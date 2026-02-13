package com.cbcode.fin;

import com.cbcode.fin.config.AppConfig;
import com.cbcode.fin.console.ConsoleMenu;
import com.cbcode.fin.report.OpexReport;
import com.cbcode.fin.report.OpexReportExcelExporter;
import com.cbcode.fin.report.OpexReportFormatter;
import com.cbcode.fin.report.ReportExporter;
import com.cbcode.fin.repository.ExcelExpenseRepository;
import com.cbcode.fin.repository.ExpenseRepository;
import com.cbcode.fin.service.ExpenseService;


import java.nio.file.Path;
import java.time.YearMonth;


public class Main {
    public static void main(String[] args) {


        AppConfig config = new AppConfig();

        ConsoleMenu menu = config.consoleMenu();

        menu.start();

    }
}