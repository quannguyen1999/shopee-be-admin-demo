package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.services.ReportService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportImpl implements ReportService {

    @Override
    public <T> byte[] printReport(List<T> data) {
        try (Workbook workbook = new XSSFWorkbook()) {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet("Sheet 1");

            // Creating a header row
            Row headerRow = sheet.createRow(0);
            Cell headerCell1 = headerRow.createCell(0);
            headerCell1.setCellValue("Name");

            Cell headerCell2 = headerRow.createCell(1);
            headerCell2.setCellValue("Age");

            // Adding data rows
            addDataRow(sheet, 1, "John Doe", 25);
            addDataRow(sheet, 2, "Jane Smith", 30);
            addDataRow(sheet, 3, "Bob Johnson", 28);

            // Writing the workbook to a ByteArrayOutputStream
            workbook.write(outputStream);

            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private static void addDataRow(Sheet sheet, int rowNum, String name, int age) {
        Row row = sheet.createRow(rowNum);
        Cell cell1 = row.createCell(0);
        cell1.setCellValue(name);

        Cell cell2 = row.createCell(1);
        cell2.setCellValue(age);
    }

}
