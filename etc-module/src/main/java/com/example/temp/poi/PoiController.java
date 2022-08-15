package com.example.temp.poi;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController

public class PoiController {

    @RequestMapping("api")
    public void handler() throws IOException {
        XSSFWorkbook workbookByNewFile = new XSSFWorkbook("C://DATA/WORK/test.xlsx");
        System.out.println("로드 완료");

        XSSFSheet sheet = workbookByNewFile.getSheetAt(0);
        sheet.getRow(13).createCell(1).setCellValue("테스트 작품 1");
        sheet.getRow(14).createCell(1).setCellValue("테스트 작품 2");
        sheet.getRow(15).createCell(1).setCellValue("테스트 작품 3");

        FileOutputStream fileout = new FileOutputStream("result.xlsx");
        workbookByNewFile.write(fileout);
    }
}
