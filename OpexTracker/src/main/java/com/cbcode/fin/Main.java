package com.cbcode.fin;

import com.cbcode.fin.console.ConsoleMenu;
import com.cbcode.fin.repository.ExcelExpenseRepository;
import com.cbcode.fin.repository.ExpenseRepository;
import com.cbcode.fin.service.ExpenseService;


public class Main {
    public static void main(String[] args) {

        ExpenseRepository repo = new ExcelExpenseRepository("expenses.xlsx");
        ExpenseService service = new ExpenseService(repo);

        ConsoleMenu menu = new ConsoleMenu(service);
        menu.start();

    }
}