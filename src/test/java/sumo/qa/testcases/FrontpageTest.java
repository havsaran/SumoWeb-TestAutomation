package sumo.qa.testcases;


import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import sumo.qa.base.TestBase;
import sumo.qa.pages.Frontpage;
import sumo.qa.util.TestUtil;
import sumo.qa.ExtentReportListener.*;

@Listeners({ TestAllureListener.class })
public class FrontpageTest extends TestBase {

	Frontpage frontpage;

	@BeforeMethod
	public void setUp() throws InterruptedException {
		driver = initialization();
		driver.get(url);
		frontpage = new Frontpage();
	}

	@Severity(SeverityLevel.BLOCKER)
	@Description("This method is checking if the loggin function works fine")
	@Feature("Loggin- checking")
	@Story("positive testcase")
	@Step("Test body.....")
	@Test(priority = 1)

	public void LogginnFunction_Test() throws InterruptedException {

		frontpage.logginnFunction();

		boolean flag = false;

		try {
			// static timeout used here on purpose
			click(getDriver(), frontpage.LogginnBtn, 3);
			Assert.assertEquals(flag, true, "Wrong username/password");
		} catch (Exception excep) {

			try {
				frontpage.GlemtPassord.click();
				// click(getDriver(), frontpage.GlemtPassord,TestUtil.ExplicitWait);

				Assert.assertEquals(flag, true, "LogginnFunction_Testcase is not clickable, caught in try block");
			} catch (Exception excep1) {
				flag = true;
				Assert.assertEquals(flag, true, "LogginnFunction_Testcase is not clickable, caught in catch block");

			}
		}
	}

	@Test(priority = 2, description = "Glemte pass")
	@Severity(SeverityLevel.BLOCKER)
	@Description("This method is checking if the foget password links works fine")
	@Feature("forget pass link- checking")
	@Story("positive testcase")
	@Step("Test body.....")
	public void GlemtPassordLink_Click_Test() {

		frontpage.clickOnGlemtPassordLink();
		Assert.assertEquals(frontpage.GlemtPassordVerifyText.getText(), "Få passord123",
				"Glemt Passordlink is not working");

	}

	@Test(priority = 3, description = "NyBruker")
	@Severity(SeverityLevel.BLOCKER)
	@Description("This method is checking if the new user link works fine")
	@Feature("Loggin- checking")
	@Story("negative testcase")
	@Step("Test body.....")
	public void NyBruker_FaatilgangNaaLink_Test() {

		frontpage.clickOn_NyBruker_FåtilgangNåBtn();
		Assert.assertEquals(driver.getTitle(), "Velkommen | TV 2 Sumo", "Asserting texts are not matchced");
	}

	@Test(priority = 4)
	@Step("Test body.....")
	public void logginnBtn_Click_Test() {

		frontpage.clickOn_LoggInnBtn();
		Assert.assertEquals(frontpage.LogginnVerifyText.getText(), "Logg inn", "Logginn Button is not working ");

	}

	@Test(priority = 5)
	@Step("Test body.....")
	public void LogginnAvbrytBtn_Click_Test() {

		boolean flag = false;
		frontpage.clickOn_LogginnAvbrytBtn();

		try {
			frontpage.GlemtPassord.click();
			Assert.assertEquals(flag, true, "LogginnAvbrytBtn_Click_Testcase- is not clickable");
		} catch (Exception e) {
			flag = true;
			Assert.assertEquals(flag, true, "LogginnAvbrytBtn_Click_Testcase- is not working");

		}
	}

	@Test(priority = 6)
	@Step("Test body.....")
	public void SumoLogo_Click_Test() {

		frontpage.clickOn_SumoLogo();

		new WebDriverWait(getDriver(),TestUtil.ExplicitWait).until(ExpectedConditions.urlToBe("https://sumo.tv2.no/"));
		String actualTitle = getDriver().getTitle();
		Assert.assertEquals(actualTitle, "TV 2 Sumo", "It's redirected to different page");
		

	}

	@AfterMethod
	public void tearDown(ITestResult result) {
	  if (driver!=null)
		driver.quit();
	}

}
