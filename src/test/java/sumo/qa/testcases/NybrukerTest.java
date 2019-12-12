package sumo.qa.testcases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import sumo.qa.base.TestBase;

import sumo.qa.pages.Nybruker;
import sumo.qa.pages.Pakker;

import sumo.qa.ExtentReportListener.*;



@Listeners ({TestAllureListener.class})
public class NybrukerTest extends TestBase {
	
	Nybruker nybruker;
	Pakker pakker;
	
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
	@Step("Test body.....")

	void goToPakkerTest() throws InterruptedException {

		pakker = nybruker.goToPakker();
	
	Assert.assertEquals(pakker.VelgPakke.isDisplayed(), true, "Assert is not match");

	}

	@AfterMethod
	public void tearDown(ITestResult result)  {

		driver.quit();
	}


}
