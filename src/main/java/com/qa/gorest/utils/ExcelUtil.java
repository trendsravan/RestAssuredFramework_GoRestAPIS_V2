package com.qa.gorest.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.qa.gorest.frameworkExcemptions.APIFrameworkException;

public class ExcelUtil {

	private static final String EXCEL_SHEET_PATH = "./src/test/resource/testdata/DatDrivenTest.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] readExcel(String sheetName) {
		Object[][] object;
		try {
			FileInputStream fis = new FileInputStream(EXCEL_SHEET_PATH);
			book = WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);
			object = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					object[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}
		} catch (FileNotFoundException e) {
			throw new APIFrameworkException("ExcelSheet FileNot Found", e);
		} catch (InvalidFormatException e) {
			throw new APIFrameworkException("FileNot ound Exception", e);
		} catch (IOException e) {
			throw new APIFrameworkException("", e);
		}
		return object;
	}
}
