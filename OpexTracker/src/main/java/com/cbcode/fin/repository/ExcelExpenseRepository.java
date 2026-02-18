package com.cbcode.fin.repository;

import com.cbcode.fin.model.Expense;
import com.cbcode.fin.model.ExpenseType;
import com.cbcode.fin.model.ServiceType;
import com.cbcode.fin.model.Vendor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExcelExpenseRepository implements ExpenseRepository {
    private final Path filePath;

    public ExcelExpenseRepository(Path filePath) {
        this.filePath = filePath;
        System.out.println("Using file: " + filePath.toAbsolutePath());
    }

    @Override
    public List<Expense> findAll() {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        List<Expense> expenses = new ArrayList<>();

        try (InputStream is = Files.newInputStream(filePath); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheet("Expenses");
            if (sheet == null) {
                return expenses;
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                UUID id = UUID.fromString(row.getCell(0).getStringCellValue());
                YearMonth date = YearMonth.parse(row.getCell(1).getStringCellValue());
                BigDecimal amount = BigDecimal.valueOf(row.getCell(2).getNumericCellValue());
                ExpenseType type = ExpenseType.valueOf(row.getCell(3).getStringCellValue());
                Vendor vendor = new Vendor(row.getCell(4).getStringCellValue());
                ServiceType serviceType = new ServiceType(row.getCell(5).getStringCellValue());

                expenses.add(new Expense(id, date, amount, type, vendor, serviceType));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read expenses from Excel", e);
        }
        return expenses;
    }

    private void createFileIfNotExists() {
        if (Files.exists(filePath)) {
            return;
        }

        try (Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Expenses");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Id");
            header.createCell(1).setCellValue("Date");
            header.createCell(2).setCellValue("Amount");
            header.createCell(3).setCellValue("Type");
            header.createCell(4).setCellValue("Vendor");
            header.createCell(5).setCellValue("ServiceType");

            try (OutputStream os = Files.newOutputStream(filePath)){
                workbook.write(os);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to create Excel file", e);
        }
    }

    @Override
    public void save(Expense expense) {
        createFileIfNotExists();

        try (InputStream is = Files.newInputStream(filePath); Workbook workbook = new XSSFWorkbook(is)){

            Sheet sheet = workbook.getSheet("Expenses");
            int lastRowNum = sheet.getLastRowNum();

            Row row = sheet.createRow(lastRowNum + 1);

            row.createCell(0).setCellValue(expense.getId().toString());
            row.createCell(1).setCellValue(expense.getDate().toString());
            row.createCell(2).setCellValue(expense.getAmount().doubleValue());
            row.createCell(3).setCellValue(expense.getType().name());
            row.createCell(4).setCellValue(expense.getVendor().name());
            row.createCell(5).setCellValue(expense.getServiceType().name());

            try (OutputStream os = Files.newOutputStream(filePath)){
                workbook.write(os);
            }

        } catch (IOException e){
            throw new RuntimeException("Failed to save expense to Excel", e);
        }
    }
}
