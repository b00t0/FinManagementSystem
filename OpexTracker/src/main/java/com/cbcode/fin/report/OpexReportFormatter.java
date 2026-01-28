package com.cbcode.fin.report;

import java.math.BigDecimal;
import java.util.Map;

public class OpexReportFormatter {
    public void printToConsole(OpexReport report){
        System.out.println("\n=== OPEX Report for " + report.getPeriod() + " ===");

        printSection("By Type", report.getByType());
        printSection("By Vendor", report.getByVendor());
        printSection("By Service Type", report.getByServiceType());

        System.out.println("\nTOTAL: " + report.getTotal());
        System.out.println();
    }

    private <K> void printSection(String title, Map<K, BigDecimal> data) {
        System.out.println("\n-- " + title + " --");

        if (data == null || data.isEmpty()) {
            System.out.println("No data");
            return;
        }

        data.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
