package sumo.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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

	@FindBy(xpath = "//a[@class='logo']//*[@class='svglogo svglogo-sumo-white']") // div[@class='header__inner']//*[@class='svglogo
																					// svglogo-sumo-white']
	private WebElement SumoLogo;
	
	@FindBy (xpath = "//span[contains(text(),'Meny')]")	
	private WebElement Meny;
	
	@FindBy (xpath = "//span[contains(text(),'Serier')]")	
	private WebElement Serier;

	

	// Initializing Page Factory
	
	public Frontpage() {

		PageFactory.initElements(getDriver(), this);

	}
	
	// Actions /functions

	public void clickOn_LoggInnBtn() {

		click(getDriver(), LogginnTriggerLink, TestUtil.ExplicitWait);

	}

	public void clickOn_NyBruker_FåtilgangNåBtn() {

		click(getDriver(), LogginnTriggerLink, TestUtil.ExplicitWait);
		click(getDriver(), NybrukerFåtilgang, TestUtil.ExplicitWait);

	}

	public void clickOnGlemtPassordLink() {

		click(getDriver(), LogginnTriggerLink, TestUtil.ExplicitWait);
		click(getDriver(), GlemtPassord, TestUtil.ExplicitWait);

	}

	public void clickOn_FåtilgangBtn() {

		click(getDriver(), FåTilgang, TestUtil.ExplicitWait);

	}

	public HomePage logginnFunction() throws InterruptedException {

		click(getDriver(), LogginnTriggerLink, TestUtil.ExplicitWait);
		sendKeys(getDriver(), UserName, TestUtil.ExplicitWait, user);
		sendKeys(getDriver(), Password, TestUtil.ExplicitWait, pass);

		// Password.sendKeys(Keys.RETURN); // by using keyboard Enter
		// LogginnBtn.click();// by using mouse

		click(getDriver(), LogginnBtn, TestUtil.ExplicitWait);
		return new HomePage();
	}

	public void clickOn_LogginnAvbrytBtn() {

		click(getDriver(), LogginnTriggerLink, TestUtil.ExplicitWait);
		click(getDriver(), AvbrytBtn, TestUtil.ExplicitWait);
	}

	public void clickOn_SumoLogo() {
		
		click(getDriver(), Meny, TestUtil.ExplicitWait);
		click(getDriver(), Serier, TestUtil.ExplicitWait);
		
		click(getDriver(), SumoLogo, TestUtil.ExplicitWait);

	}

}
