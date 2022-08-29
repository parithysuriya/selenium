package coaching;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Apache_ReadExcel {
	
	WebDriver driver;
    WebDriverWait wait;
    HSSFWorkbook workbook;
    HSSFSheet sheet;
    HSSFCell cell;
    																																																																																																																													
    
  @Test
  public void ReadData() throws IOException {
	  
	  System.setProperty("webdriver.chrome.driver", "F:\\Suriya\\chromedriver.exe");	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://localhost:3000/");
		
		// Import excel sheet.
	     File src=new File("F:\\Suriya\\suri.xls");
	      
	     // Load the file.
	     FileInputStream finput = new FileInputStream(src);
	      
	     try (// Load the workbook.
	HSSFWorkbook workbook = new HSSFWorkbook(finput)) {
		// Load the sheet in which data is stored.
	   HSSFSheet  sheet= workbook.getSheetAt(0);
	     
	     for(int i=0; i<sheet.getLastRowNum(); i++)
	     {
	         // Import data for Email.
	         cell = sheet.getRow(i).getCell(0);
	         cell.setCellType(cell.getCellType());
	         driver.findElement(By.id("userName")).sendKeys(cell.getStringCellValue());
	          
	         // Import data for password.
	         cell = sheet.getRow(i).getCell(1);
	         cell.setCellType(cell.getCellType());
	         driver.findElement(By.id("pwd")).sendKeys(cell.getStringCellValue());
	         
	          
//	         // Write data in the excel.
//	       FileOutputStream foutput=new FileOutputStream(src);
//	         
//	        // Specify the message needs to be written.
//	        String message = "Data Imported Successfully.";
	         
	        // Create cell where data needs to be written.
//	        sheet.getRow(i).createCell(3).setCellValue(message);
	          
	        // Specify the file in which data needs to be written.
//	        FileOutputStream fileOutput = new FileOutputStream(src);
	         
//	        // finally write content
//	        workbook.write(fileOutput);
//	         
//	         // close the file
//	        fileOutput.close();
	     
  }
	}
}
}
