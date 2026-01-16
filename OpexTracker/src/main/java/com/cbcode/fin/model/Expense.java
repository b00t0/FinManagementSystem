package com.cbcode.fin.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    private LocalDate date;
    private BigDecimal amount;
    private ExpenseType type;
    private Vendor vendor;
    private ServiceType serviceType;

    public Expense(LocalDate date, BigDecimal amount, ExpenseType type, Vendor vendor, ServiceType serviceType) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.vendor = vendor;
        this.serviceType = serviceType;
    }

    public LocalDate getDate() {
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
                "date=" + date +
                ", amount=" + amount +
                ", type=" + type +
                ", vendor=" + vendor +
                ", serviceType=" + serviceType +
                '}';
    }
}
