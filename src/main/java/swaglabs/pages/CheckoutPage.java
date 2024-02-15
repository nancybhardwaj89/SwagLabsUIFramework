package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swaglabs.utils.ElementUtil;

public class CheckoutPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private By checkoutPageHeader = By.xpath("//span[@class='title']");
	private By firstName = By.id("first-name");
	private By lastName = By.id("last-name");
	private By zipCode = By.id("postal-code");
	private By continueBtn = By.id("continue");

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	public String getCheckoutPageHeader() {
		return elementUtil.doGetText(checkoutPageHeader);
	}

	public CheckoutOverviewPage clickonContinue() {
		elementUtil.doSendKeys(firstName, "Nancy");
		elementUtil.doSendKeys(lastName, "Bhardwaj");
		elementUtil.doSendKeys(zipCode, "123456");
		elementUtil.doClick(continueBtn);
		return new CheckoutOverviewPage(driver);

	}
}
