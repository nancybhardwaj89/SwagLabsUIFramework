package swaglabs.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import swaglabs.listeners.AllureReportListener;
import swaglabs.utils.Constants;

@Listeners(AllureReportListener.class)
public class CheckoutOverviewPageTest extends BaseTest {
	@BeforeClass
	public void checkoutOverviewSetup() {
		prodlistingPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		proDetailsPage = prodlistingPage.selectProduct("Sauce Labs Bike Light");
		cartPage = proDetailsPage.clickonCartandIcon();
		checkoutPage = cartPage.clickonCheckout();
		checkoutoverviewPage = checkoutPage.clickonContinue();
	}

	@Test(priority = 1)
	public void orderComHeaderTest() {
		ordercomPage = checkoutoverviewPage.clickonFinish();
		String successHeader = ordercomPage.getSuccessHeaderText();
		System.out.println("Sucess Header is:" + successHeader);
		Assert.assertEquals(successHeader, Constants.ORDER_COMPLETE_PAGE_HEADER);

	}
}
