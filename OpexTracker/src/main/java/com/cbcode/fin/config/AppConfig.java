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

        Path path = Path.of("expenses.xlsx");
        ExpenseRepository repository = new ExcelExpenseRepository(path);
        ExpenseService expenseService = new ExpenseService(repository);
        ReportExporter exporter = new OpexReportExcelExporter();
        return new ConsoleMenu(expenseService, exporter);
    }
}
