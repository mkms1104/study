package com.example.temp.poi;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class HelloPoi {

    @Test
    public void xssfWorkbook() throws IOException {
        try(XSSFWorkbook workbook = new XSSFWorkbook("220630_PKC_US.UK_UA_Weekly_Report_Creatives.xlsx")) {
            System.out.println("로드 완료");
        }
    }

    @Test
    public void sxssfWorkbook() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook("220630_PKC_US.UK_UA_Weekly_Report_Creatives.xlsx");
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook);
        sxssfWorkbook.setCompressTempFiles(true);
    }

    @Test
    public void aaa() throws IOException {
        try(XSSFWorkbook workbookByNewFile = new XSSFWorkbook("test.xlsx")) {
            System.out.println("로드 완료");

            XSSFSheet sheet = workbookByNewFile.getSheetAt(0);
            sheet.getRow(13).createCell(1).setCellValue("테스트 작품 1");
            sheet.getRow(14).createCell(1).setCellValue("테스트 작품 2");
            sheet.getRow(15).createCell(1).setCellValue("테스트 작품 3");

            FileOutputStream fileout = new FileOutputStream("result.xlsx");
            workbookByNewFile.write(fileout);
        }
    }
}
