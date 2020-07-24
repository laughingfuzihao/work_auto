package com.gwssi.work_auto;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
class WorkAutoApplicationTests {

    @Test
    void contextLoads() throws IOException {
        String basePath = "D:\\worktime\\";
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("11111");
        FileOutputStream fileOutputStream = new FileOutputStream(basePath+"text.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();


    }

}
