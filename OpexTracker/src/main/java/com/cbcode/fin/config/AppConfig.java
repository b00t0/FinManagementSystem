package com.cbcode.fin.config;

import com.cbcode.fin.console.ConsoleMenu;
import com.cbcode.fin.report.OpexReportExcelExporter;
import com.cbcode.fin.report.ReportExporter;
import com.cbcode.fin.repository.ExcelExpenseRepository;
import com.cbcode.fin.repository.ExpenseRepository;
import com.cbcode.fin.service.ExpenseService;

import java.nio.file.Path;

public class AppConfig {

    public ConsoleMenu consoleMenu() {
        return new ConsoleMenu(expenseService(), reportExporter());
    }

    private ExpenseService expenseService() {
        return new ExpenseService(expenseRepository());
    }

    private ExpenseRepository expenseRepository() {
        Path path = Path.of("expenses.xlsx");
        return new ExcelExpenseRepository(path);
    }

    private ReportExporter reportExporter() {
        return new OpexReportExcelExporter();
    }
}

