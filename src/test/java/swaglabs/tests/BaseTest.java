package swaglabs.tests;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import swaglabs.factory.DriverFactory;
import swaglabs.pages.CartPage;
import swaglabs.pages.CheckoutOverviewPage;
import swaglabs.pages.CheckoutPage;
import swaglabs.pages.LoginPage;
import swaglabs.pages.OrderCompletePage;
import swaglabs.pages.ProductDetailsPage;
import swaglabs.pages.ProductListingPage;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	Properties prop;
	LoginPage loginpage;
	ProductListingPage prodlistingPage;
	ProductDetailsPage proDetailsPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	CheckoutOverviewPage checkoutoverviewPage;
	OrderCompletePage ordercomPage;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProperties();
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
