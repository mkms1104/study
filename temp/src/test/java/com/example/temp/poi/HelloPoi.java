package com.example.temp.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloPoi {

    @Test
    public void test() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(""));
    }
}
