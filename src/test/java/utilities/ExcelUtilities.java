package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtilities(String path) {
		this.path = path;
	}
	
	public int getrowCount(String sheetName) {
		int rowCount = 0;
		try {
			fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			rowCount = sheet.getLastRowNum();
			workbook.close();
			fi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public int getcellCount(String sheetName, int rowNum) {
		int cellCount = 0;
		try {
			fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			if (row != null) {
	            cellCount = row.getLastCellNum();
	        } else {
	            cellCount = 0; // Row doesn't exist, so no cells
	        }
			workbook.close();
			fi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
			String data;
			fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
		    if (row == null) {
		        workbook.close();
		        fi.close();
		        return ""; // or handle as needed
		    }

		    cell = row.getCell(colNum);
		    if (cell == null) {
		        workbook.close();
		        fi.close();
		        return ""; // or handle as needed
		    }

			
			DataFormatter dateFormatter = new DataFormatter();
			
			
			try {
				data = dateFormatter.formatCellValue(cell);
			}
			catch (Exception e) {
				data="";
			}
			workbook.close();
			fi.close();	
		
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		File xlFile = new File(path);
		if (!xlFile.exists()) {     // if file does not exist, create a new one
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		if (workbook.getSheetIndex(sheetName) == -1) {  // if sheet does not exist, create a new Sheet
			workbook.createSheet(sheetName);
			sheet = workbook.getSheet(sheetName);	
		}
		if (sheet.getRow(rowNum) == null) {  // if row does not exist, create a new Row
			sheet.createRow(rowNum);
			row = sheet.getRow(rowNum);
		}
		
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void fillRedColor(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		if (row == null) {
	        row = sheet.createRow(rowNum);
	    }
	    cell = row.getCell(colNum);
	    if (cell == null) {
	        cell = row.createCell(colNum);
	    }

		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fi.close(); // close input before writing
	    fo = new FileOutputStream(path);
	    workbook.write(fo);
	    workbook.close();
	    fo.close();
	}
	
	public void fillGreenColor(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);

		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		fi.close(); // close input before writing
	    fo = new FileOutputStream(path);
	    workbook.write(fo);
	    workbook.close();
	    fo.close();
	}
	
}
