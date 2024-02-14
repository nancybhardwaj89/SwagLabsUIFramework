package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import swaglabs.utils.Constants;
import swaglabs.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By username = By.id("user-name");
	
	private By password = By.id("password");
	private By loginBtn = By.id("login-button");
	private By loginPgHeader = By.xpath("//div[@class='login_logo']");
	
	//constructor
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	@Step("Getting Login Page Title")
	public String getLoginPageTitle()
	{
		return elementUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, 5);
	}
	
	@Step("Getting Login Page Header Text")
	public String getLoginPageHeaderText()
	{
		return elementUtil.doGetText(loginPgHeader);
	}
	
	@Step("Login to Swag Labs with username {0} and password {1}")
	public ProductListingPage doLogin(String un, String pwd)
	{
		//driver.findElement(username).sendKeys(un);
		//driver.findElement(password).sendKeys(pwd);
		//driver.findElement(loginBtn).click();
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		return new ProductListingPage(driver);
	}

}
