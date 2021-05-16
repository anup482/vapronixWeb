package com.vapronixweb.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

static final String filepath = "Test Data/testData.xlsx";
	

	public static String readData(String sheetName,int rowNum,int cellNum) 
	{
		String data="";
		try
		{
		
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File(filepath)));
		data=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		}
		
		
		catch(EncryptedDocumentException e)
		{
			e.printStackTrace();
		}
		
		catch(InvalidFormatException e)
		{
			e.printStackTrace();
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
		return data;
	}
}
