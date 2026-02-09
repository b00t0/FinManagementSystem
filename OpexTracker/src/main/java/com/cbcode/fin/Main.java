package com.cbcode.fin;

import com.cbcode.fin.console.ConsoleMenu;
import com.cbcode.fin.report.OpexReport;
import com.cbcode.fin.report.OpexReportExcelExporter;
import com.cbcode.fin.report.OpexReportFormatter;
import com.cbcode.fin.repository.ExcelExpenseRepository;
import com.cbcode.fin.repository.ExpenseRepository;
import com.cbcode.fin.service.ExpenseService;


import java.nio.file.Path;
import java.time.YearMonth;


public class Main {
    public static void main(String[] args) {

        ExpenseRepository repo = new ExcelExpenseRepository("expenses.xlsx");
        ExpenseService service = new ExpenseService(repo);
        OpexReportExcelExporter exporter = new OpexReportExcelExporter();

        ConsoleMenu menu = new ConsoleMenu(service, exporter);
        menu.start();

    }
}