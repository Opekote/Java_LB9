package org.university;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JSONToXLSXTest {

    private String tempFilename;
    private File file;

    @BeforeEach
    public void setUp() {
        tempFilename = System.getProperty("java.io.tmpdir") + "/testExcel";
        file = new File(tempFilename + ".xlsx");
        file.delete();

    }

    @AfterEach
    public void tearDown() {
        file = new File(tempFilename + ".xlsx");
        file.delete();

    }

    @Test
    public void testConvertWithNewFile() throws IOException {
        // Modify the sample JSON content
        String modifiedJson = "{ \"employees\": [ { \"firstName\": \"Alice\", \"lastName\": \"Johnson\", \"age\": 28 }, { \"firstName\": \"Bob\", \"lastName\": \"Smith\", \"age\": 35 } ] }";

        JSONToXLSX.convert(modifiedJson, tempFilename, "employees", "testSheet");

        assertTrue(new File(tempFilename + ".xlsx").exists());

        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(tempFilename + ".xlsx"))) {
            Sheet sheet = workbook.getSheet("testSheet");
            assertNotNull(sheet);

            Row headerRow = sheet.getRow(0);
            assertEquals("firstName", headerRow.getCell(0).getStringCellValue());
            assertEquals("lastName", headerRow.getCell(1).getStringCellValue());
            assertEquals("age", headerRow.getCell(2).getStringCellValue());

            Row firstDataRow = sheet.getRow(1);
            assertEquals("Alice", firstDataRow.getCell(0).getStringCellValue());
            assertEquals("Johnson", firstDataRow.getCell(1).getStringCellValue());
            assertEquals("28", firstDataRow.getCell(2).getStringCellValue());
        }
    }

    @Test
    public void testConvertWithExistingSheetName() {
        // Modify the sample JSON content
        String modifiedJson = "{ \"employees\": [ { \"firstName\": \"John\" } ] }";

        JSONToXLSX.convert(modifiedJson, tempFilename, "employees", "testSheet");

        // Try converting again with the same sheet name, should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> JSONToXLSX.convert(modifiedJson, tempFilename, "employees", "testSheet"));
    }
}
