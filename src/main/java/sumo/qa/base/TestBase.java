package sumo.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import sumo.qa.util.TestUtil;
import sumo.qa.util.Xls_Reader;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;

	public ExtentReports extent;
	public ExtentTest logger;

	static Xls_Reader reader = new Xls_Reader("C:\\Users\\speriy\\Desktop\\xlData.xlsx");
	static int RowNo = 3;
	public static String url = reader.getCellData("Sheet1", "url", RowNo);
	public static String browserName = reader.getCellData("Sheet1", "browser", RowNo);

	public  String user = reader.getCellData("Sheet1", "username", RowNo);
	public  String pass = reader.getCellData("Sheet1", "password", RowNo);

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
	public static void initialization() throws InterruptedException {
				
		//String browserName = prop.getProperty("browser");

		String OS = System.getProperty("os.name");

		if (OS.toUpperCase().contains("WINDOWS")) {

			if (browserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Files needed for Testing Automation\\chromedriver_win32\\chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equals("FF")) {
				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
				driver = new FirefoxDriver();
			}
		}

		else if (OS.toUpperCase().contains("MAC")) {

			if (browserName.equals("chrome")) {
				 System.setProperty("webdriver.chrome.driver", "C:\\Files needed for Testing Automation\\chromedriver_win32\\chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", "chromedriver");
				driver = new ChromeDriver();
			} else if (browserName.equals("FF")) {
				System.setProperty("webdriver.gecko.driver", "geckodriver");
				driver = new FirefoxDriver();
			}
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PageLoadTimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.ImplicityWait, TimeUnit.SECONDS);
		 
		//driver.get(prop.getProperty("url"));
			driver.get(url);
			
		Thread.sleep(TestUtil.sleepTime);

	}

}