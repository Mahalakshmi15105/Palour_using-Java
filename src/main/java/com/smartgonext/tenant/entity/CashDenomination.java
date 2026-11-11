package com.smartgonext.tenant.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cash_denominations")
public class CashDenomination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "count_500")
    private Integer count500 = 0;

    @Column(name = "count_200")
    private Integer count200 = 0;

    @Column(name = "count_100")
    private Integer count100 = 0;

    @Column(name = "count_50")
    private Integer count50 = 0;

    @Column(name = "count_20")
    private Integer count20 = 0;

    @Column(name = "count_10")
    private Integer count10 = 0;

    @Column(name = "count_5")
    private Integer count5 = 0;

    @Column(name = "count_2")
    private Integer count2 = 0;

    @Column(name = "count_1")
    private Integer count1 = 0;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "opening_balance", precision = 10, scale = 2)
    private BigDecimal openingBalance = BigDecimal.ZERO;

    public CashDenomination() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Branch getBranch() { return branch; }
    public void setBranch(Branch branch) { this.branch = branch; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Integer getCount500() { return count500; }
    public void setCount500(Integer count500) { this.count500 = count500; }

    public Integer getCount200() { return count200; }
    public void setCount200(Integer count200) { this.count200 = count200; }

    public Integer getCount100() { return count100; }
    public void setCount100(Integer count100) { this.count100 = count100; }

    public Integer getCount50() { return count50; }
    public void setCount50(Integer count50) { this.count50 = count50; }

    public Integer getCount20() { return count20; }
    public void setCount20(Integer count20) { this.count20 = count20; }

    public Integer getCount10() { return count10; }
    public void setCount10(Integer count10) { this.count10 = count10; }

    public Integer getCount5() { return count5; }
    public void setCount5(Integer count5) { this.count5 = count5; }

    public Integer getCount2() { return count2; }
    public void setCount2(Integer count2) { this.count2 = count2; }

    public Integer getCount1() { return count1; }
    public void setCount1(Integer count1) { this.count1 = count1; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public BigDecimal getOpeningBalance() { return openingBalance; }
    public void setOpeningBalance(BigDecimal openingBalance) { this.openingBalance = openingBalance; }
}
