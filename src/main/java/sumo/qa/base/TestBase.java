package sumo.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import sumo.qa.util.GoogleSheetAPI;
import sumo.qa.util.TestUtil;

public class TestBase {

	public WebDriver driver;
	public static Properties prop;

	// Thread concept (tdriver here) is required for generating Allure report -
	// Especially getting same report for multiple class execution
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

//	public ExtentReports extent;
//	public ExtentTest logger;

//	static Xls_Reader reader = new Xls_Reader("C:\\Users\\speriy\\Desktop\\SumoWebData.xlsx");
//	static int RowNo = 2;
//	public static String url = reader.getCellData("Sheet1", "url", RowNo);
//	public static String browserName = reader.getCellData("Sheet1", "browser", RowNo);
//
//	public String user = reader.getCellData("Sheet1", "username", RowNo);
//	public String pass = reader.getCellData("Sheet1", "password", RowNo);

	static GoogleSheetAPI sheetreader = new GoogleSheetAPI();
	static int RowNo = 1;

	public static String url = sheetreader.getSpreadSheetRecordsCellData(RowNo, 0);
	public String user = sheetreader.getSpreadSheetRecordsCellData(RowNo, 1);
	public String pass = sheetreader.getSpreadSheetRecordsCellData(RowNo, 2);
	public static String browserName = sheetreader.getSpreadSheetRecordsCellData(RowNo, 3);

// 	TestBase constructor
	public TestBase() {

		// for reading data from config.properties file

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/sumo/qa/config" + "/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

// Browser initialization
	public WebDriver initialization() throws InterruptedException {

		// String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PageLoadTimeOut, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(TestUtil.ImplicityWait, TimeUnit.SECONDS);
		Thread.sleep(TestUtil.sleepTime);

		tdriver.set(driver);
		// driver.get(prop.getProperty("url"));

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}

	public static void click(WebDriver driver, WebElement element, long timeout) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	public static Boolean display(WebDriver driver, WebElement element, long timeout) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
		return element.isDisplayed();
	}

	public static void sendKeys(WebDriver driver, WebElement element, long timeout, String value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}
}