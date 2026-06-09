package com.sky.test;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class POITest {
    public static void write() throws IOException {
        //在内存中创建一个Excel文件
        XSSFWorkbook excel = new XSSFWorkbook();
        //在Excel文件中创建一个Sheet页
        XSSFSheet sheet = excel.createSheet("info");
        //在Sheet页中创建一行 rowNum从0开始
        XSSFRow row = sheet.createRow(1);
        //在行中创建一个单元格 cellNum从0开始
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("城市");

        row = sheet.createRow(2);
        row.createCell(0).setCellValue(1);
        row.createCell(1).setCellValue("张三");
        row.createCell(2).setCellValue("北京");

        //把内存的这个excel文件写到磁盘中来
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/ptx/Desktop/itheima.xlsx");
        excel.write(fileOutputStream);

        excel.close();
        fileOutputStream.close();
    }

    public static void read() throws Exception{
        InputStream in=new FileInputStream(new File("/Users/ptx/Desktop/itheima.xlsx"));
        XSSFWorkbook excel = new XSSFWorkbook(in);
        XSSFSheet sheet = excel.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum();
        for(int i=1;i<=lastRowNum;i++){
            XSSFRow row = sheet.getRow(i);
            String cellValue1 = row.getCell(1).getStringCellValue();
            String cellValue2 = row.getCell(2).getStringCellValue();
            System.out.println(cellValue1+"--"+cellValue2);
        }
        excel.close();
        in.close();

    }

    public static void main(String[] args) {
        try {
           //write();
            read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
