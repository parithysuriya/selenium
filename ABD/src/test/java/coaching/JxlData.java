package coaching;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

// DATA DRIVEN TESTING USING JXL Jar (allow only xls file)
public class JxlData {
	
	WebDriver driver;
	
	String [][] data = null;
	
	@DataProvider(name="loginData")
	
	public String [][] loginDataProvider() throws BiffException, IOException{
		data=getExcelData();
	// string change to object	
	// jxl jar used only xls format(97-2003 worksheet)
		return data;
		
	}
	  public String[][] getExcelData() throws BiffException, IOException{
		  
		  FileInputStream excel = new FileInputStream("F:\\Suriya\\suri.xls");
		  
		 
		  Workbook workbook = Workbook.getWorkbook(excel);
		  
		  Sheet sheet = workbook.getSheet(0); // sheet name
		  
		  int rowCount = sheet.getRows();
		  
		  int columnCount = sheet.getColumns();
		  
		  String testData[][] = new String[rowCount-1][columnCount];
		  
		  for (int i=1; i<rowCount;i++) {
			  for (int j=0;j<columnCount;j++) {
				  testData[i-1][j]=sheet.getCell(j, i).getContents(); 
			  }
		  }
		   return testData;
	  }
	
  @Test(dataProvider="loginData")
  public void login(String user, String pass) {
	  System.setProperty("webdriver.chrome.driver", "F:\\Suriya\\chromedriver.exe");	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://localhost:3000/");
		
		WebElement username = driver.findElement(By.id("userName"));
		username.sendKeys(user);

		WebElement password = driver.findElement(By.id("pwd"));
		password.sendKeys(pass);

		WebElement login = driver.findElement(By.cssSelector(".btn:nth-child(3)"));
		login.click();
	  // ch
  }
}
