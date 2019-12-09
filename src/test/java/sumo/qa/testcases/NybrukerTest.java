package sumo.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import sumo.qa.base.TestBase;

import sumo.qa.pages.Nybruker;
import sumo.qa.pages.Pakker;

import sumo.qa.ExtentReportListener.*;



@Listeners ({TestAllureListener.class})
public class NybrukerTest extends TestBase {
	//WebDriver driver;
	Nybruker nybruker;
	Pakker pakker;
	
//	public  ExtentReports extent;
//	public  ExtentTest extentTest;

//	@BeforeTest
//	public void setExtent() {
//		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
//		
//	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		driver = initialization();
		driver.get(url);
				nybruker = new Nybruker();
		}

	@Test (description="PAkker")
	@Severity(SeverityLevel.BLOCKER)	
	@Description ("This method is checking if the package displaying link works fine")
	@Feature ("pacakge display- checking")
	@Story ("positive testcase")

	void goToPakkerTest() throws InterruptedException {
//		extentTest = extent.startTest("goToPakkerTest_Testcase");
		pakker = nybruker.goToPakker();
	
	Assert.assertEquals(pakker.VelgPakke.isDisplayed(), true, "Assert is notttttt matcehddddd");

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
//		if (result.getStatus() == ITestResult.FAILURE) {
//			extentTest.log(LogStatus.FAIL, "Test Case Failed -" + result.getName());// to add name in extent Report
//			extentTest.log(LogStatus.FAIL, "Test Case Failed -" + result.getThrowable());// to add error/exception in
//																							// extent report
//
//			String screenshotPath = FrontpageTest.getScreenshot(driver, result.getName());
//
//			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));// to add screenshot in extent
//																						// report
//		} else if (result.getStatus() == ITestResult.SKIP) {
//			extentTest.log(LogStatus.SKIP, result.getName());
//		} else if (result.getStatus() == ITestResult.SUCCESS) {
//			extentTest.log(LogStatus.PASS, result.getName());
//		}
//		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
		driver.quit();
	}

//	@AfterTest
//	public void endReport() {
//		extent.flush();
//		extent.close();
//	}

}
