package com.cbcode.fin.service;

import com.cbcode.fin.model.Expense;
import com.cbcode.fin.model.ExpenseType;
import com.cbcode.fin.repository.ExpenseRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

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
}
