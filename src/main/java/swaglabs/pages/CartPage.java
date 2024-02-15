package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swaglabs.utils.ElementUtil;

public class CartPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private By cartPageHeader = By.xpath("//span[@class='title']");
	private By prodHeader = By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Bike Light']");
	private By checkoutBtn = By.id("checkout");

	public CartPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getCartPageHeader() {
		return elementUtil.doGetText(cartPageHeader);
	}

	public Boolean isProdHeaderDisplayed() {
		return elementUtil.doIsDisplayed(prodHeader);
	}

	public CheckoutPage clickonCheckout() {
		elementUtil.doClick(checkoutBtn);
		return new CheckoutPage(driver);
	}
}
