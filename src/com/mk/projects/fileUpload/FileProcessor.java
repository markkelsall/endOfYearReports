package com.mk.projects.fileUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileProcessor {

	private Logger logger = LogManager.getLogger(getClass().getName());
	
	public void process (String filePath) {
		
		logger.trace("Entering FileProcessor.process()");
		
		try {
			FileInputStream file = new FileInputStream(new File(filePath)); 
			
			//Create Workbook instance from file input stream
            XSSFWorkbook workbook = new XSSFWorkbook(file);
			
            //Get sheet
            XSSFSheet sheet = workbook.getSheet("Year 4");
            
            //get the first row with the students name and then break
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                
                //iterate through all the columns until there isn't a value
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) 
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "t");
                            break;
                    }
                }
            }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.trace("Exiting FileProcessor.process()");
	}
}
