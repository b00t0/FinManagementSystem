package com.cbcode.fin.service;

import com.cbcode.fin.model.Expense;
import com.cbcode.fin.model.ExpenseType;
import com.cbcode.fin.model.ServiceType;
import com.cbcode.fin.model.Vendor;
import com.cbcode.fin.report.OpexReport;
import com.cbcode.fin.repository.ExpenseRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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

    private <K> Map<K, BigDecimal> groupBy(YearMonth period, Function<Expense, K> classifier) {
        return repository.findAll().stream()
                .filter(e -> e.getDate().equals(period))
                .collect(Collectors.groupingBy(classifier, Collectors.mapping(Expense::getAmount, Collectors.reducing(
                        BigDecimal.ZERO, BigDecimal::add
                )
                )
                ));
    }

    public OpexReport generateOpexReport(YearMonth period) {
        Map<ExpenseType, BigDecimal> byType = groupBy(period, Expense::getType);

        Map<Vendor, BigDecimal> byVendor = groupBy(period, Expense::getVendor);

        Map<ServiceType, BigDecimal> byServiceType = groupBy(period, Expense::getServiceType);

        BigDecimal total = byType.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OpexReport(period, total, byType, byVendor, byServiceType);
    }

}
