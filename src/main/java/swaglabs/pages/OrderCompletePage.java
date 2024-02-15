package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swaglabs.utils.ElementUtil;

public class OrderCompletePage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private By successHeader = By.xpath("//h2[@class='complete-header' and text()='Thank you for your order!']");

	public OrderCompletePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getSuccessHeaderText() {
		return elementUtil.doGetText(successHeader);
	}
}
