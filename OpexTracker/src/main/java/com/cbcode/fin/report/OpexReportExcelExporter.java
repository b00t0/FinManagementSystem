package com.cbcode.fin.report;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class OpexReportExcelExporter implements ReportExporter {

    public void export(OpexReport report, Path path) {

        try (Workbook workbook = new XSSFWorkbook();
             OutputStream os = Files.newOutputStream(path)) {

            createSummarySheet(workbook, report);

            createMapSheet(workbook, "By Type", report.getByType());
            createMapSheet(workbook, "By Vendor", report.getByVendor());
            createMapSheet(workbook, "By Service Type", report.getByServiceType());

            workbook.write(os);

        } catch (IOException e) {
            throw new RuntimeException("Failed to export OPEX report", e);
        }
    }

    private void createSummarySheet(Workbook workbook, OpexReport report) {
        Sheet sheet = workbook.createSheet("Summary");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Period");
        header.createCell(1).setCellValue("Total");

        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue(report.getPeriod().toString());
        row.createCell(1).setCellValue(report.getTotal().doubleValue());
    }

    private <T> void createMapSheet(Workbook workbook, String sheetName, Map<T, BigDecimal> data) {

        Sheet sheet = workbook.createSheet(sheetName);

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Category");
        header.createCell(1).setCellValue("Total");

        int rowIdx = 1;

        for (Map.Entry<T, BigDecimal> entry : data.entrySet()) {
            Row row = sheet.createRow(rowIdx++);

            row.createCell(0).setCellValue(entry.getKey().toString());
            row.createCell(1).setCellValue(entry.getValue().doubleValue());
        }
    }

    @Override
    public void export(OpexReport report) {

    }
}
