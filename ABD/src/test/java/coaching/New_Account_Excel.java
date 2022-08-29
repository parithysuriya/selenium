package coaching;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class New_Account_Excel {
  @Test
  public void excelread() throws IOException {
	  System.setProperty("webdriver.chrome.driver", "F:\\Suriya\\chromedriver.exe");	
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://localhost:3000/Login/createaccount");
		
		//Create an object of File class to open xlsx file
        File file =    new File("F:\\Suriya\\test.xlsx");
        
      //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        
      //creating workbook instance that refers to .xlsx file
        XSSFWorkbook wb=new XSSFWorkbook(inputStream);
        
      //creating a Sheet object
        XSSFSheet sheet=wb.getSheet("data");
        
      //get all rows in the sheet
        int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
        
        // Identify the web elements
        WebElement username = driver.findElement(By.id("username"));
        
        WebElement password = driver.findElement(By.id("password"));
        
        //iterate over all the rows in Excel and put data in the form.
        
        for(int i=1;i<=rowCount;i++) {
        	username.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
            password.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
            
          //create a new cell in the row at index 6
          //  XSSFCell cell = sheet.getRow(i).createCell(6);
            
         // Write the data back in the Excel file
           // FileOutputStream outputStream = new FileOutputStream("F:\\Suriya\\test.xlsx");
           // wb.write(outputStream);
            
          //Close the workbook
           // wb.close();
        }
        //driver.close();
	}
  }

