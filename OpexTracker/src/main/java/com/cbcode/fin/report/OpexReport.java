package com.cbcode.fin.report;

import com.cbcode.fin.model.ExpenseType;
import com.cbcode.fin.model.ServiceType;
import com.cbcode.fin.model.Vendor;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;

public class OpexReport {

    private final YearMonth period;
    private final BigDecimal total;
    private final Map<ExpenseType, BigDecimal> byType;
    private final Map<Vendor, BigDecimal> byVendor;
    private final Map<ServiceType, BigDecimal> byServiceType;

    public OpexReport(YearMonth period,BigDecimal total, Map<ExpenseType, BigDecimal> byType, Map<Vendor, BigDecimal> byVendor, Map<ServiceType, BigDecimal> byServiceType) {
        this.period = period;
        this.total = total;
        this.byType = byType;
        this.byVendor = byVendor;
        this.byServiceType = byServiceType;
    }

    public YearMonth getPeriod() {
        return period;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Map<ExpenseType, BigDecimal> getByType() {
        return byType;
    }

    public Map<Vendor, BigDecimal> getByVendor() {
        return byVendor;
    }

    public Map<ServiceType, BigDecimal> getByServiceType() {
        return byServiceType;
    }
}
