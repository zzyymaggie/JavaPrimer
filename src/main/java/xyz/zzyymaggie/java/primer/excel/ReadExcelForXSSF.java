package xyz.zzyymaggie.java.primer.excel;

import org.apache.poi.ss.usermodel.*;

import java.io.*;

/**
 * read excel 2007+
 * @author zhangyu
 * @date 09/21/2018
 */
public class ReadExcelForXSSF {
    public void read(File file) {
        InputStream inputStream;
        Workbook workbook;
        try {
            inputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum()+1;
            //工作表的列
            Row row = sheet.getRow(0);
            //总列数
            int colLength = row.getLastCellNum();
            //得到指定的单元格
            Cell cell = row.getCell(0);
            System.out.println("行数：" + rowLength + ",列数：" + colLength);
            for (int i = 0; i < rowLength; i++) {
                row = sheet.getRow(i);
                for (int j = 0; j < colLength; j++) {
                    cell = row.getCell(j);
                    if(cell != null) {
                        System.out.print(cell.toString() + "\t");
                    }else{
                        System.out.print("\t");
                    }
                }
                System.out.println();
            }

            //将修改好的数据保存
            OutputStream out = new FileOutputStream(file);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ReadExcelForXSSF().read(new File("tmp.xlsx"));
    }
}
