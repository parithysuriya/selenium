package coaching;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Login_Page {
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest; //helps to generate the logs in test report.
	
	@BeforeTest
	public void setExtent(){
		// initialize the HtmlReporter
		extent = new ExtentReports("./src/test/resources/Reports/LoginPageReport.html", true); // true - new data insert into report,false-append the old data
		//To add system or environment info by using the addSystemInfo method.
		extent.addSystemInfo("User Name", "Suriya");
		extent.addSystemInfo("Environment", "Automation Testing");
		extent.addSystemInfo("Application","Learning Online Course "); 
		extent.addSystemInfo("Test Scenario","Login Functionality");

	}
	@AfterTest
	public void endReport(){

		extent.flush(); // Flush method is used to erase any previous data on the report and create a new report.
		//extent.close(); 
	}
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{	
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	@BeforeMethod
	public void setup() throws InterruptedException, IOException {	

		System.setProperty("webdriver.chrome.driver", "F:\\Suriya\\chromedriver.exe");	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://localhost:3000/");
	}

	@Test(enabled=true,priority=0,description="Test case - Both User name and Password are entered correctly")
	public void username_and_password_correct() throws IOException {
		
		extentTest = extent.startTest("Enter correct username and password");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement username = driver.findElement(By.id("userName"));
		username.sendKeys("suriya");

		WebElement password = driver.findElement(By.id("pwd"));
		password.sendKeys("suriya12@7#");

		WebElement login = driver.findElement(By.cssSelector(".btn:nth-child(3)"));
		
		
		
		login.click();

	}
	
	@Test(enabled=true,priority=1,description="Test case - Both Username and Password Fields are blank")
	public void user_and_password_blank() throws InterruptedException {
      
		extentTest = extent.startTest("Username and Password fields are blank");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement username = driver.findElement(By.id("userName"));
		username.sendKeys("");

		WebElement password = driver.findElement(By.id("pwd"));
		password.sendKeys("");

		WebElement login = driver.findElement(By.cssSelector(".btn:nth-child(3)"));
		login.click();
	
	}
	@Test(enabled=true,priority=2,description="Test case - Enter correct username and Password field is blank")
	public void username_filled_and_password_blank() throws InterruptedException, IOException {
		extentTest = extent.startTest("Enter correct username and password field is blank");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement username = driver.findElement(By.id("userName"));
		username.sendKeys("suriya");
		
		WebElement password = driver.findElement(By.id("pwd"));
		password.sendKeys("");
		
		WebElement login = driver.findElement(By.cssSelector(".btn:nth-child(3)"));
		login.click();
		
		
	}
	@Test(enabled=true,priority=3,description="Test case - Enter invalid username & invalid password")
	public void Enter_invalid_username_and_password() throws InterruptedException {
		extentTest = extent.startTest("Enter invalid username and invalid password ");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement username = driver.findElement(By.id("userName"));
		username.sendKeys("suriyaparithy");
		
		WebElement password = driver.findElement(By.id("pwd"));
		password.sendKeys("suriya2460");
		
		WebElement login = driver.findElement(By.cssSelector(".btn:nth-child(3)"));
		login.click();
	}
	
	@AfterMethod
	public void Down(ITestResult result) throws IOException{

		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report

			String screenshotPath = getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}


		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}
}
