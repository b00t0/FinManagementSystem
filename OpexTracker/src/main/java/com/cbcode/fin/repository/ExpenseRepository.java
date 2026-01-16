package com.cbcode.fin.repository;

import com.cbcode.fin.model.Expense;

import java.util.List;

public interface ExpenseRepository {
    void save(Expense expense);
    List<Expense> findAll();
}
