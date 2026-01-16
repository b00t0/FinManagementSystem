package com.cbcode.fin;

import com.cbcode.fin.model.Expense;
import com.cbcode.fin.model.ExpenseType;
import com.cbcode.fin.model.ServiceType;
import com.cbcode.fin.model.Vendor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Vendor vendor1 = new Vendor("Interprodresurs");
        ServiceType service1 = new ServiceType("Rent");

        Vendor vendor2 = new Vendor("Vympelcom");
        ServiceType service2 = new ServiceType("Telecommunications");

        Expense expense1 = new Expense(YearMonth.of(2026, Month.JANUARY), new BigDecimal("5000"), ExpenseType.FIXED, vendor1, service1);
        Expense expense2 = new Expense(YearMonth.of(2026, Month.JANUARY), new BigDecimal("1200.50"), ExpenseType.VARIABLE, vendor2, service2);

        System.out.println(expense1);
        System.out.println(expense2);
        
    }
}