package coterie.Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import coterie.AbstractComponents.AbstractComponent;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

public class Readxls{
		
	@DataProvider(name="Data")
	public String[][] getData(Method m) throws IOException, IOException {
		
		String excelSheetName = m.getName();
		File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\data\\testData.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);

		Sheet sheetName = wb.getSheet (excelSheetName);
		int totalRows =sheetName.getLastRowNum();
		//System.out.println(totalRows);
		Row rowCells = sheetName.getRow(0);
		int totalCols = rowCells.getLastCellNum();
		
		//System.out.println(totalCols);
		DataFormatter format = new DataFormatter();
		String testData[][] = new String[totalRows] [totalCols];
		for(int i=1;i<=totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				testData[i - 1][j] = format.formatCellValue(sheetName.getRow(i).getCell(j));
			//	System.out.println(testData[i - 1][j]);
			}
		}
		return testData;

	}
}