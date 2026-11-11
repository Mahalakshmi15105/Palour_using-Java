package com.smartgonext.config.multitenancy;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    private final Map<Object, Object> targetDataSources = new ConcurrentHashMap<>();
    private final String urlPrefix;
    private final String urlSuffix;
    private final String username;
    private final String password;
    private final String driverClassName;

    public TenantRoutingDataSource(String urlPrefix, String urlSuffix,
                                   String username, String password,
                                   String driverClassName, DataSource defaultDataSource) {
        this.urlPrefix = urlPrefix;
        this.urlSuffix = urlSuffix;
        this.username = username;
        this.password = password;
        this.driverClassName = driverClassName;

        this.targetDataSources.put(TenantContext.DEFAULT_TENANT, defaultDataSource);
        super.setTargetDataSources(this.targetDataSources);
        super.setDefaultTargetDataSource(defaultDataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getTenantId();
    }

    @Override
    protected DataSource determineTargetDataSource() {
        Object lookupKey = determineCurrentLookupKey();
        if (lookupKey == null) {
            lookupKey = TenantContext.DEFAULT_TENANT;
        }

        DataSource dataSource = (DataSource) targetDataSources.get(lookupKey);
        if (dataSource == null) {
            synchronized (this) {
                dataSource = (DataSource) targetDataSources.get(lookupKey);
                if (dataSource == null) {
                    dataSource = createDataSourceForTenant(lookupKey.toString());
                    targetDataSources.put(lookupKey, dataSource);
                    super.setTargetDataSources(targetDataSources);
                    super.afterPropertiesSet();
                }
            }
        }
        return dataSource;
    }

    private DataSource createDataSourceForTenant(String tenantDbName) {
        HikariDataSource ds = new HikariDataSource();
        String jdbcUrl = urlPrefix + tenantDbName + urlSuffix;
        ds.setJdbcUrl(jdbcUrl);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driverClassName);
        ds.setPoolName("TenantPool-" + tenantDbName);
        ds.setMaximumPoolSize(10);
        ds.setMinimumIdle(2);
        ds.setIdleTimeout(30000);
        ds.setConnectionTimeout(20000);
        return ds;
    }
}
