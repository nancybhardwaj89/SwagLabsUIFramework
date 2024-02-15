package swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import swaglabs.listeners.AllureReportListener;
import swaglabs.utils.Constants;

@Listeners(AllureReportListener.class)
public class CheckoutPageTest extends BaseTest {
	@BeforeClass
	public void checkoutPageSetup() {
		prodlistingPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		proDetailsPage = prodlistingPage.selectProduct("Sauce Labs Bike Light");
		cartPage = proDetailsPage.clickonCartandIcon();
		checkoutPage = cartPage.clickonCheckout();
	}

	@Test(priority = 1)
	public void checkoutOverviewTest() {
		checkoutoverviewPage = checkoutPage.clickonContinue();
		String checkoutOverviewHeader = checkoutoverviewPage.getOverviewPageHeaderText();
		Assert.assertEquals(checkoutOverviewHeader, Constants.CHECKOUTOVERVIEW_PAGE_HEADER);

	}
}
