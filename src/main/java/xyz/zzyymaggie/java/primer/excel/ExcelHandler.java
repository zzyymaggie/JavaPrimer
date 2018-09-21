package xyz.zzyymaggie.java.primer.excel;

import java.io.File;

/**
 * @author zhangyu
 * @date 09/21/2018
 */
public class ExcelHandler {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    public static void read(File file){
        if (file.getName().endsWith(EXCEL_XLS)) {//Excel 2003
            new ReadExcelForHSSF().read(file);
            System.out.println("Excel file type:xls");
        } else if (file.getName().endsWith(EXCEL_XLSX)) {// Excel 2007/2010
           new ReadExcelForXSSF().read(file);
            System.out.println("Excel file type:xlsx");
        }
    }

    public static void main(String[] args) {
        ExcelHandler.read(new File("tmp.xlsx"));
        ExcelHandler.read(new File("tmp.xls"));
    }
}

