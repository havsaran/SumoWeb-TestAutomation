package sumo.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sumo.qa.base.TestBase;

public class Pakker extends TestBase {

	@FindBy(xpath = "//span[contains (text(), 'Velg pakke')]")
	public WebElement VelgPakke;

	public Pakker() {
		PageFactory.initElements(getDriver(), this);
	}

}
