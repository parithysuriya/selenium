package coaching;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Apache_poi_xls {
	
	WebDriver driver;
	//WebDriverWait wait;
	HSSFWorkbook workbook;  // xls sheet
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
		
				
		FileInputStream finput = new FileInputStream("F:\\Suriya\\suri.xls");
		
		workbook = new HSSFWorkbook (finput);
		
		sheet = workbook.getSheetAt(0);
		
		for(int i=1; i<=sheet.getLastRowNum(); i++)
		{
			
			 String user=sheet.getRow(i).getCell(0).getStringCellValue();
	         String password=sheet.getRow(i).getCell(1).getStringCellValue();
	         
	         
			// cell = sheet.getRow(i).getCell(0);
			 
			 driver.findElement(By.id("userName")).sendKeys(user);
			 
			// cell = sheet.getRow(i).getCell(1);
			 
			 driver.findElement(By.id("pwd")).sendKeys(password);
			 
			 driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
		}
		
		// 
	  
  }
}
