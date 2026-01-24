package com.cbcode.fin;

import com.cbcode.fin.console.ConsoleMenu;
import com.cbcode.fin.model.Expense;
import com.cbcode.fin.model.ExpenseType;
import com.cbcode.fin.model.ServiceType;
import com.cbcode.fin.model.Vendor;
import com.cbcode.fin.repository.ExcelExpenseRepository;
import com.cbcode.fin.repository.ExpenseRepository;
import com.cbcode.fin.repository.InMemoryExpenseRepository;
import com.cbcode.fin.service.ExpenseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        ExpenseRepository repository = new InMemoryExpenseRepository();
//        ExpenseService service = new ExpenseService(new InMemoryExpenseRepository());

        ExpenseRepository repo = new ExcelExpenseRepository("expenses.xlsx");
//
        ExpenseService service = new ExpenseService(repo);
//
//        service.addExpense(
//                new Expense(
//                        YearMonth.now(),
//                        new BigDecimal("1000"),
//                        ExpenseType.FIXED,
//                        new Vendor("UK45"),
//                        new ServiceType("Rent")
//                )
//        );
//
//       service.getAllExpenses().forEach(System.out::println);

        ConsoleMenu menu = new ConsoleMenu(service);
        menu.start();
//        Vendor vendor1 = new Vendor("Interprodresurs");
//        ServiceType service1 = new ServiceType("Rent");
//
//        Vendor vendor2 = new Vendor("Vympelcom");
//        ServiceType service2 = new ServiceType("Telecommunications");
//
//        service.addExpense(new Expense(YearMonth.of(2026, Month.JANUARY), new BigDecimal("5000"), ExpenseType.FIXED, vendor1, service1));
//        service.addExpense(new Expense(YearMonth.of(2026, Month.JANUARY), new BigDecimal("1200.50"), ExpenseType.VARIABLE, vendor2, service2));
//
//        System.out.println("All the expenses:");
//        service.getAllExpenses().forEach(System.out::println);
//
//        System.out.println();
//        System.out.println("FIXED for Jan'26: " + service.getTotalByType(ExpenseType.FIXED, YearMonth.of(2026, Month.JANUARY)));

    }
}