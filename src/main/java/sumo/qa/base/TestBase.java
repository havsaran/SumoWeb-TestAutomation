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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import sumo.qa.util.GoogleSheetAPI;
import sumo.qa.util.TestUtil;

public class TestBase {

	public WebDriver driver;
	public static Properties prop;

	/*
	 * Thread concept (tdriver here) is required for generating Allure report -
	 * Especially getting same report for multiple class execution
	 */
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

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

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PageLoadTimeOut, TimeUnit.SECONDS);

		tdriver.set(driver);

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}

	// custom methods for explicit watit- to be used in page and test classes

	public static void click(WebDriver driver, WebElement element, long timeout) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static Boolean display(WebDriver driver, WebElement element, long timeout) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOf(element));
		return element.isDisplayed();
	}

	public static void sendKeys(WebDriver driver, WebElement element, long timeout, String value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}
}

/*
 * public static String getScreenshot(WebDriver driver, String screenshotName)
 * throws IOException {
 * 
 * String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
 * 
 * TakesScreenshot ts = (TakesScreenshot) driver; File source =
 * ts.getScreenshotAs(OutputType.FILE); // after execution, you could see a
 * folder "FailedTestsScreenshots"
 * 
 * String destination = System.getProperty("user.dir") +
 * "/FailedTestsScreenshots/" + screenshotName + dateName + ".png"; File
 * finalDestination = new File(destination); FileUtils.copyFile(source,
 * finalDestination); return destination;
 * 
 * }
 */