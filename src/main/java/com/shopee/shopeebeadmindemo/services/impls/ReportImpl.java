package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.services.ReportService;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportImpl implements ReportService {

    @Override
    public byte[] printReport(List<HashMap<String, Object>> listData,
                              List<String> listFields) {
        try (Workbook workbook = new XSSFWorkbook()) {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet("Sheet 1");

            // Creating a header row
            addCells(listFields, sheet);

            // Adding data rows
            addDataRows(listData, listFields, sheet);

            // Writing the workbook to a ByteArrayOutputStream
            workbook.write(outputStream);

            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private static void addDataRows(List<HashMap<String, Object>> listData,
                                    List<String> listFields,
                                    Sheet sheet) throws NoSuchFieldException {
        for (int locate = 0; locate < listData.size(); locate++) {
            Row row = sheet.createRow(locate + 1);
            for (int locateField = 0; locateField < listFields.size(); locateField++) {
                Cell cell1 = row.createCell(locateField);
                Object data = listData.get(locate).getOrDefault(listFields.get(locateField), null);
                cell1.setCellValue(ObjectUtils.isEmpty(data) ? Strings.EMPTY : data.toString());
            }
        }
    }

    private static void addCells(List<String> listFields, Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < listFields.size(); i++) {
            Cell headerCell1 = headerRow.createCell(i);
            headerCell1.setCellValue(listFields.get(i));
        }
    }

}
