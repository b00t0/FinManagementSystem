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
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Stream<Expense> expensesForPeriod(YearMonth period) {
        return repository.findAll().stream()
                .filter(e -> e.getDate().equals(period));
    }

    private <K> Map<K, BigDecimal> aggregateBy(YearMonth period, Function<Expense, K> classifier) {
        return expensesForPeriod(period)
                .collect(Collectors.groupingBy(classifier, Collectors.reducing(
                        BigDecimal.ZERO,
                        Expense::getAmount,
                        BigDecimal::add
                )
                ));
    }

    public BigDecimal getTotalByType(ExpenseType type, YearMonth period) {
        return expensesForPeriod(period)
                .filter(e -> e.getType() == type)
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<Vendor, BigDecimal> getTotalByVendor(YearMonth period) {
        return aggregateBy(period, Expense::getVendor);
    }

    public Map<ServiceType, BigDecimal> getTotalByServiceType(YearMonth period) {
        return aggregateBy(period, Expense::getServiceType);
    }
}
