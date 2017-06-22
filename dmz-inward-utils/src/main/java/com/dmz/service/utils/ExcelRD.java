package com.dmz.service.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by dmz on 2016/3/22.
 */
public class ExcelRD {

    public static void ReadExcel(String source) {
        try (FileInputStream fin = new FileInputStream(source);
             Workbook wb = new XSSFWorkbook(fin)) {

            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i);
                System.out.println("Sheet:" + wb.getSheetName(i));
                int rows = sheet.getPhysicalNumberOfRows();

                for (int j = 0; j < rows; j++) {
                    Row row = sheet.getRow(j);
                    int cells = row.getPhysicalNumberOfCells();
                    System.out.println("Row:"+row.getRowNum()+" has "+cells + " cell(s)");

                    for (int k = 0;k<cells;k++){
                        Cell cell = row.getCell(k);
                        System.out.print(getCellComment(cell) + " ");
                    }
                    System.out.println();

                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCellComment(Cell cell) {

        String value = null;

        switch (cell.getCellType()) {

            case HSSFCell.CELL_TYPE_FORMULA:
                value = cell.getCellFormula();
                break;

            case HSSFCell.CELL_TYPE_NUMERIC:

                value = String.format("%.0f", cell.getNumericCellValue());
                break;

            case HSSFCell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;

            default:

        }
        return value;
    }
}
