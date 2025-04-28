package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "loginData")
	public String[][] getData() throws IOException {
		String path = ".\\testData\\Opencart_LoginData.xlsx";  // Path to the Excel file
		ExcelUtilities excelutil = new ExcelUtilities(path);  // Create an instance of ExcelUtilities with the path to the Excel file
		int totalRowCount = excelutil.getrowCount("Sheet1");
		int totalColumnCount = excelutil.getcellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRowCount-1][totalColumnCount];  // Create a 2D array to hold the login data
		
		for (int i = 1; i < totalRowCount; i++) { //1 //read the data from excel stored in two dimensional array
			for (int j = 0; j < totalColumnCount; j++) {  //0 i is row number and j is column number
				loginData[i - 1][j] = excelutil.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
	}
	
}
