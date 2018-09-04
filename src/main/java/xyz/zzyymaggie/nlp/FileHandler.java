package xyz.zzyymaggie.nlp;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class FileHandler {
 // 读取csv文件的内容
    public static ArrayList<String[]> readCsv(String filepath) {
        // 用来保存数据
        ArrayList<String[]> csvFileList = new ArrayList<String[]>();
        try {
            // 定义一个CSV路径
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(filepath, ',', Charset.forName("UTF-8"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
//                System.out.println(reader.getRawRecord()); 
                csvFileList.add(reader.getValues()); 
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvFileList;
    }
    
    
    public void writeCSV(String path, ArrayList<String[]> writearraylist) {
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter csvWriter = new CsvWriter(path, ',', Charset.forName("UTF-8"));
            // 写表头
            String[] csvHeaders = { "domain", "title", "description", "keywords", "textrank" };
            String[] resultLine = new String[5];
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            for (int i = 0; i < writearraylist.size(); i++) {
                String[] csvContent = writearraylist.get(i);
                if(csvContent[0].equals("") && csvContent[1].equals("")) {
                    continue;
                }
                for(int j=0;j<4;j++) {
                    resultLine[j] = csvContent[j];
                }
                String content = "";
                if(csvContent.length > 2) {
                    if(!csvContent[2].equals("")) {
                        content  = csvContent[2];
                    }else {
                        content = csvContent[1];
                    }
                }
                resultLine[4] = new TextRankKeyword().getKeyword("", content);
                csvWriter.writeRecord(resultLine);
            }
            csvWriter.close();
            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        FileHandler handler = new FileHandler();
        ArrayList<String[]> list = readCsv("in1.csv");
       handler.writeCSV("out1.csv", list);
    }
}
