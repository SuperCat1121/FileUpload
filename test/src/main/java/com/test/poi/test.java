package com.test.poi;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class test {

	public static void main(String[] args) {
		XSSFWorkbook wb = null;
		FileInputStream input = null;
		try {
			input = new FileInputStream(new File("C:/Users/user2/Desktop/test.xlsx"));
			wb = new XSSFWorkbook(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Sheet test = wb.getSheetAt(0);
		Row row = test.getRow(0);
		int LastRowNum = test.getLastRowNum();
		System.out.println(LastRowNum);
		
		List<String> monthList = new ArrayList<String>();
		List<Double> numList = new ArrayList<Double>();
		for(int i=1;i<=LastRowNum;i++) {
			monthList.add(test.getRow(i).getCell(0).getStringCellValue());
			numList.add(test.getRow(i).getCell(1).getNumericCellValue());
		}
		
		for(int i=0;i<monthList.size();i++) {
			System.out.print(monthList.get(i) + " ");
			System.out.print(numList.get(i) + " ");
		}
	}

}
