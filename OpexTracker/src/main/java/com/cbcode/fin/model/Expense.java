package com.cbcode.fin.model;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

public class Expense {
    private final UUID id;
    private YearMonth date;
    private BigDecimal amount;
    private ExpenseType type;
    private Vendor vendor;
    private ServiceType serviceType;

    public Expense(UUID id, YearMonth date, BigDecimal amount, ExpenseType type, Vendor vendor, ServiceType serviceType) {
        this.id = UUID.randomUUID();
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.vendor = vendor;
        this.serviceType = serviceType;
    }

    public UUID getId() {
        return id;
    }

    public YearMonth getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public ExpenseType getType() {
        return type;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id" + id +
                "date=" + date +
                ", amount=" + amount +
                ", type=" + type +
                ", vendor=" + vendor +
                ", serviceType=" + serviceType +
                '}';
    }
}
