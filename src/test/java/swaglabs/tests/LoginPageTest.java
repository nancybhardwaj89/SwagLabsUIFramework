package swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import swaglabs.listeners.AllureReportListener;
import swaglabs.pages.ProductListingPage;
import swaglabs.utils.Constants;

@Listeners(AllureReportListener.class)
public class LoginPageTest extends BaseTest {
	
	@Description("Login Page Title Test")
	@Severity (SeverityLevel.NORMAL)
	@Test (priority = 1)
	public void loginPageTitleTest()
	{
		String loginPageTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(loginPageTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Login Page Header Test")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 2)
	public void loginPageHeaderTest()
	{
		String loginHeader = loginpage.getLoginPageHeaderText();
		System.out.println("Login Page Header is:" +loginHeader);
		Assert.assertEquals(loginHeader, Constants.LOGIN_PAGE_HEADER);
	}
	
	@Description("Checking Login Credentails")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority =3)
	public void loginTest()
	{
		ProductListingPage prodlistingPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(prodlistingPage.getProductListHeader(), Constants.PRODUCT_LIST_HEADER);
	}

}
