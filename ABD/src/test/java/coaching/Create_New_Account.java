package coaching;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Create_New_Account {
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest; //helps to generate the logs in test report.
	
	@BeforeTest
	public void setExtent(){
		// initialize the HtmlReporter
		extent = new ExtentReports("./src/test/resources/Reports/NewAccountReport.html", true); // true - new data insert into report,false-append the old data
		//To add system or environment info by using the addSystemInfo method.
		extent.addSystemInfo("User Name", "Suriya");
		extent.addSystemInfo("Environment", "Automation Testing");
		extent.addSystemInfo("Application","Learning Online Course "); 
		extent.addSystemInfo("Test Scenario","Create New Account");

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
  @Test
  public void Create_Account() throws InterruptedException {
	  extentTest = extent.startTest("Enter Valid Details in Create Account");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	  driver.get("http://localhost:3000/Login/createaccount");
	  
	    driver.findElement(By.id("username")).sendKeys("suriya");

	    driver.findElement(By.id("password")).sendKeys("suriya@123");

	    driver.findElement(By.id("uniqueId")).sendKeys("2");

	    driver.findElement(By.name("standard")).sendKeys("12");

	    driver.findElement(By.name("schoolName")).sendKeys("Rm.p.s ramaiah hr sec school");
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,1000)");

	    driver.findElement(By.name("fatherName")).sendKeys("Elamparithy");

	    driver.findElement(By.name("fatherPhoneNo")).sendKeys("8098896779");

	    driver.findElement(By.name("motherName")).sendKeys("Panchavarnam");
	  
	    driver.findElement(By.name("motherPhoneNo")).sendKeys("8098896779");

	    driver.findElement(By.name("contactNo")).sendKeys("8344418512");

	    driver.findElement(By.name("email")).sendKeys("suriyaparithy@gmail.com");

	    driver.findElement(By.name("idProof")).sendKeys("2");

	    WebElement course = driver.findElement(By.id("course"));
	    course.findElement(By.xpath("//option[. = 'NEET']")).click();
	    
	    WebElement image = driver.findElement(By.name("image"));
	  // image.click();
	    image.sendKeys("F:\\Suriya\\ui.png");
	    
	    //Thread.sleep(8000);
	    
	   driver.findElement(By.xpath("/html/body/div/div/div/form/button")).submit();
	    
	    
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

