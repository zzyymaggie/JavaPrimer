package xyz.zzyymaggie.java.primer.excel;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcelForXSSF {
    public void write() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("0");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("年龄");
        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("zhangyu");
        row1.createCell(1).setCellValue("18");
        workbook.setSheetName(0, "信息");
        try {
            File file = new File("POI3.xlsx");
            FileOutputStream fileoutputStream = new FileOutputStream(file);
            workbook.write(fileoutputStream);
            fileoutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new WriteExcelForXSSF().write();
    }
}
