package com.cbcode.fin.service;

import com.cbcode.fin.model.Expense;
import com.cbcode.fin.model.ExpenseType;
import com.cbcode.fin.model.ServiceType;
import com.cbcode.fin.model.Vendor;
import com.cbcode.fin.repository.ExpenseRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseService {
    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public void addExpense(Expense expense){
        repository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    public BigDecimal getTotalByType(ExpenseType type, YearMonth period) {
        return repository.findAll().stream()
                .filter(e -> e.getType() == type)
                .filter(e -> e.getDate().equals(period))
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<Vendor, BigDecimal> getTotalByVendor(YearMonth period) {
        return repository.findAll().stream()
                .filter(expense -> expense.getDate().equals(period))
                .collect(Collectors.groupingBy(
                        Expense::getVendor,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Expense::getAmount,
                                BigDecimal::add
                        )
                ));
    }

    public Map<ServiceType, BigDecimal> getTotalByServiceType(YearMonth period) {
        return repository.findAll().stream()
                .filter(e -> e.getDate().equals(period))
                .collect(Collectors.groupingBy(
                        Expense::getServiceType,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Expense::getAmount,
                                BigDecimal::add
                        )
                ));
    }
}
