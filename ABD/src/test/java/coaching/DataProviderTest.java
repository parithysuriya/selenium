package coaching;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
	WebDriver driver;
	
	@DataProvider(name = "Authentication")

	  public static Object[][] credentials() {

	        // The number of times data is repeated, test will be executed the same no. of times

	        // Here it will execute two times

	        return new Object[][] { { "suriya", "Test@123" }, { "parithy", "Test@123" }};

	  }
	
	 @Test(dataProvider = "Authentication")

  public void test(String username, String password) {
		 System.setProperty("webdriver.chrome.driver", "F:\\Suriya\\chromedriver.exe");	
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get("http://localhost:3000/");
			
			WebElement username1 = driver.findElement(By.id("userName"));
			username1.sendKeys(username);

			WebElement password1 = driver.findElement(By.id("pwd"));
			password1.sendKeys(password);

			WebElement login = driver.findElement(By.cssSelector(".btn:nth-child(3)"));
			
			login.click();
	   
	  
  }
}
