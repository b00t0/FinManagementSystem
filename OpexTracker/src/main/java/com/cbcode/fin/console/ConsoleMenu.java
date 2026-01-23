package com.cbcode.fin.console;

import com.cbcode.fin.model.Expense;
import com.cbcode.fin.model.ExpenseType;
import com.cbcode.fin.model.ServiceType;
import com.cbcode.fin.model.Vendor;
import com.cbcode.fin.service.ExpenseService;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class ConsoleMenu {
    private final ExpenseService expenseService;
    private final Scanner scanner = new Scanner(System.in);


    public ConsoleMenu(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addExpense();
                case "2" -> showAllExpenses();
                case "3" -> showMonthlyReports();
                case "0" -> running = false;
                default -> System.out.println("Unknown option. Try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                ===== OPEX Expense Tracker =====
                1. Add expense
                2. Show all expenses
                3. Show monthly reports
                0. Exit
                """);
    }

    private void addExpense() {
        try {
            BigDecimal amount = readBigDecimal("Enter amount: ");
            YearMonth date = readYearMonth("Enter date (yyyy-MM): ");
            ExpenseType expenseType = readEnum("Select expense type: ", ExpenseType.values());

            String vendorName = readString("Enter vendor name: ");
            String serviceTypeName = readString("Enter service type: ");

            Vendor vendor = new Vendor(vendorName);
            ServiceType serviceType = new ServiceType(serviceTypeName);

            Expense expense = new Expense(
                    date,
                    amount,
                    expenseType,
                    vendor,
                    serviceType
            );
            expenseService.addExpense(expense);
            System.out.println("Expense added successfully");
        } catch (Exception e) {
            System.out.println("Failed to add expense: " + e.getMessage());
        }

    }

    private void showAllExpenses() {
        expenseService.getAllExpenses()
                .forEach(System.out::println);
    }

    private void showMonthlyReports() {
        YearMonth period = readYearMonth("Enter month (yyyy-MM): ");

        System.out.println("\n--- Monthly OPEX Report for " + period + " ---");

        BigDecimal fixedTotal = expenseService.getTotalByType(ExpenseType.FIXED, period);
        BigDecimal variableTotal = expenseService.getTotalByType(ExpenseType.VARIABLE, period);

        System.out.println("Fixed expenses total: " + fixedTotal);
        System.out.println("Variable expenses total: " + variableTotal);

        System.out.println("\nBy Vendor");
        expenseService.getTotalByVendor(period)
                .forEach((vendor, amount) ->
                        System.out.println(" - " + vendor + ": " + amount));

        System.out.println("\nBy Service Type: ");
        expenseService.getTotalByServiceType(period)
                .forEach((serviceType, amount) ->
                        System.out.println(" - " + serviceType + ": " + amount));

    }

    private BigDecimal readBigDecimal(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private String readString(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Value cannot be empty.");
        }
    }

    private YearMonth readYearMonth(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return YearMonth.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Use yyyy-MM.");
            }
        }
    }

    private <T extends Enum<T>> T readEnum(String prompt, T[] values) {
        while (true) {
            System.out.println(prompt);
            for (int i = 0; i < values.length; i++) {
                System.out.println((i + 1) + ". " + values[i]);
            }

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= values.length) {
                    return values[choice - 1];
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println("Invalid choice. Try again.");
        }
    }
}
