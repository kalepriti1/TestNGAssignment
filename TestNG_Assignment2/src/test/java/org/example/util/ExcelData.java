package org.example.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelData {
    public ExcelData() {
    }

    public static String getData(String filepath, String sheetname, int rn, int cn) {
        try {
            FileInputStream file = new FileInputStream(filepath);
            Workbook wb = WorkbookFactory.create(file);
            Row r = wb.getSheet(sheetname).getRow(rn);
            String data = r.getCell(cn).getStringCellValue();
            return data;
        } catch (FileNotFoundException var8) {
            throw new RuntimeException(var8);
        } catch (Exception var9) {
            return "";
        }
    }
}

