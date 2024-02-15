package swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import swaglabs.listeners.AllureReportListener;
import swaglabs.utils.Constants;

@Listeners(AllureReportListener.class)
public class CartPageTest extends BaseTest {

	@BeforeClass
	public void cartPageSetup() {
		prodlistingPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		proDetailsPage = prodlistingPage.selectProduct("Sauce Labs Bike Light");
		cartPage = proDetailsPage.clickonCartandIcon();
	}

	@Test(priority = 1)
	public void prodHeaderTest() {
		Assert.assertTrue(cartPage.isProdHeaderDisplayed());
	}

	@Test(priority = 2)
	public void checkoutPageHeaderTest() {
		checkoutPage = cartPage.clickonCheckout();
		String checkoutPagTxt = checkoutPage.getCheckoutPageHeader();
		Assert.assertEquals(checkoutPagTxt, Constants.CHECKOUT_PAGE_HEADER);

	}
}
