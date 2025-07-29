package com.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WriteToExcel {
    private static Workbook workbook;
    private static Map<String, Sheet> sheets = new HashMap<>();
    private static String filePath = "TestOutput/TestExecutionLogs.xlsx";

    static {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                fis.close();

                // Clear all existing data from sheets
                Iterator<Sheet> iterator = workbook.sheetIterator();
                while (iterator.hasNext()) {
                    Sheet sheet = iterator.next();
                    clearSheet(sheet); // Clear rows
                }

                // OR: Remove all sheets completely if you want to start fresh
                // while (workbook.getNumberOfSheets() > 0) {
                //     workbook.removeSheetAt(0);
                // }
            } else {
                workbook = new XSSFWorkbook();
            }

            // Save cleared workbook
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void log(String sheetName, String message) {
        // Print to console
        System.out.println(message);

        // Write to Excel
        Sheet sheet;
        if (sheets.containsKey(sheetName)) {
            sheet = sheets.get(sheetName);
        } else {
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
            sheets.put(sheetName, sheet);
        }

        int lastRow = sheet.getLastRowNum();
        Row row = sheet.createRow(lastRow + 1);
        Cell cell = row.createCell(0);
        cell.setCellValue(message);

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearSheet(Sheet sheet) {
        for (int i = sheet.getLastRowNum(); i >= 0; i--) {
            Row row = sheet.getRow(i);
            if (row != null) {
                sheet.removeRow(row);
            }
        }
    }
}
