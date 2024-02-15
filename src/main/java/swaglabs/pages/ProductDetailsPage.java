package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swaglabs.utils.ElementUtil;

public class ProductDetailsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private By selectedProd = By
			.xpath("//div[@class='inventory_details_name large_size' and text()='Sauce Labs Bike Light']");
	private By addtoCartBtn = By.id("add-to-cart-sauce-labs-bike-light");
	private By cartIcon = By.xpath("//a[@class='shopping_cart_link']");

	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getSelectedProd() {
		return elementUtil.doGetText(selectedProd);
	}

	public CartPage clickonCartandIcon() {
		elementUtil.doClick(addtoCartBtn);
		elementUtil.doClick(cartIcon);
		return new CartPage(driver);
	}
}
