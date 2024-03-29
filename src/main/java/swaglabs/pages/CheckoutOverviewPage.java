package swaglabs.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swaglabs.utils.ElementUtil;

public class CheckoutOverviewPage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	private By checkoutOverviewPgHeader = By.xpath("//span[@class='title' and text()='Checkout: Overview']");
	private By finishBtn = By.id("finish");

	public CheckoutOverviewPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	public String getOverviewPageHeaderText() {
		return elementUtil.doGetText(checkoutOverviewPgHeader);
	}

	public OrderCompletePage clickonFinish() {
		elementUtil.doClick(finishBtn);
		return new OrderCompletePage(driver);
	}
}
