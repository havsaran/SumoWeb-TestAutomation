package sumo.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sumo.qa.base.TestBase;
import sumo.qa.util.TestUtil;

public class Frontpage extends TestBase {

	// Page Factory

	@FindBy(xpath = "//span[text()= \"Logg inn\"]")
	private WebElement LogginnTriggerLink;
	@FindBy(xpath = "//h1[contains(text(), 'Logg')]")
	public WebElement LogginnVerifyText;

	@FindBy(xpath = "//span[contains(text(),'Få tilgang')]")
	public WebElement FåTilgang;

	@FindBy(partialLinkText = "ikke bruker")
	private WebElement NybrukerFåtilgang;
	// @FindBy (xpath = "// a [contains (text(), 'Har du ikke bruker? Få tilgang
	// her')] ")
	// public WebElement NybrukerFåtilgangverifyText;

	@FindBy(xpath = "//button[contains(text(),'Glemt passord?')]")
	public WebElement GlemtPassord;
	@FindBy(xpath = "//span[contains(text(),'Få passord')]")
	public WebElement GlemtPassordVerifyText;

	// Page Factory: For Logginn Purpose
	@FindBy(name = "user_name")
	private WebElement UserName;

	@FindBy(name = "password")
	private WebElement Password;

	@FindBy(xpath = "//span[@class='button__text'][contains(text(),'Logg inn')]//parent::button")
	public WebElement LogginnBtn;

	@FindBy(xpath = "//span[@class='dropdown__trigger-text']")
	public WebElement ValidateLoggedinnPage;

	// Avbryt botton
	@FindBy(xpath = "// span[contains (text(), 'Avbryt')]//parent::button")
	// @FindBy (xpath = "//button[@class='button2 button--secondary']")
	private WebElement AvbrytBtn;

	// SumoLogo/image

	@FindBy(xpath = "//a[@class='logo']//*[@class='svglogo svglogo-sumo-white']")//div[@class='header__inner']//*[@class='svglogo svglogo-sumo-white']
	private WebElement SumoLogo;

	// Actions /functions

	public Frontpage() {

		PageFactory.initElements(driver, this);

	}

	public void clickOn_LoggInnBtn() {
		LogginnTriggerLink.click();
	}

	public void clickOn_NyBruker_FåtilgangNåBtn() {
		LogginnTriggerLink.click();

		NybrukerFåtilgang.click();
	}

	public void clickOnGlemtPassordLink() {
		LogginnTriggerLink.click();
		GlemtPassord.click();
	}

	public void clickOn_FåtilgangBtn() {

		FåTilgang.click();
	}

	public HomePage logginnFunction() throws InterruptedException {

		LogginnTriggerLink.click();

//	String user = prop.getProperty("username");
//	String pass = prop.getProperty("password");

		UserName.sendKeys(user);
		Password.sendKeys(pass);

		// Password.sendKeys(Keys.RETURN); // by using keyboard Enter
		Thread.sleep(1500);
		LogginnBtn.click();// by using mouse
		Thread.sleep(3000);
		return new HomePage ();
	}

	public void clickOn_LogginnAvbrytBtn() {
		LogginnTriggerLink.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AvbrytBtn.click();

	}

	public void clickOn_SumoLogo() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		SumoLogo.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
