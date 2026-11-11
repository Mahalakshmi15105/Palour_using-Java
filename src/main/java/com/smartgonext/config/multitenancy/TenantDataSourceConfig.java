package com.smartgonext.config.multitenancy;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.smartgonext.tenant.repository",
        entityManagerFactoryRef = "tenantEntityManagerFactory",
        transactionManagerRef = "tenantTransactionManager"
)
public class TenantDataSourceConfig {

    @Value("${app.datasource.tenant.url-prefix:jdbc:mysql://localhost:3306/}")
    private String urlPrefix;

    @Value("${app.datasource.tenant.url-suffix:?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true}")
    private String urlSuffix;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.jpa.hibernate.ddl-auto:update}")
    private String ddlAuto;

    @Bean(name = "defaultTenantDataSource")
    public DataSource defaultTenantDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(urlPrefix + TenantContext.DEFAULT_TENANT + urlSuffix);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driverClassName);
        ds.setPoolName("TenantPool-Default-" + TenantContext.DEFAULT_TENANT);
        ds.setMaximumPoolSize(10);
        ds.setMinimumIdle(2);
        return ds;
    }

    @Bean(name = "tenantDataSource")
    @Primary
    public DataSource tenantDataSource(@Qualifier("defaultTenantDataSource") DataSource defaultTenantDataSource) {
        return new TenantRoutingDataSource(
                urlPrefix,
                urlSuffix,
                username,
                password,
                driverClassName,
                defaultTenantDataSource
        );
    }

    @Bean(name = "tenantEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("tenantDataSource") DataSource tenantDataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        return builder
                .dataSource(tenantDataSource)
                .packages("com.smartgonext.tenant.entity")
                .persistenceUnit("tenantPU")
                .properties(properties)
                .build();
    }

    @Bean(name = "tenantTransactionManager")
    @Primary
    public PlatformTransactionManager tenantTransactionManager(
            @Qualifier("tenantEntityManagerFactory") EntityManagerFactory tenantEntityManagerFactory) {
        return new JpaTransactionManager(tenantEntityManagerFactory);
    }
}

