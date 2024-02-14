package swaglabs.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import swaglabs.listeners.AllureReportListener;
import swaglabs.utils.Constants;

@Listeners(AllureReportListener.class)
public class ProductListingPageTest extends BaseTest {
	
	@BeforeClass
	public void prodListSetup()
	{
		prodlistingPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	
	
	
	@Description("Product Listing Page Header Test")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 1)
	public void productListHeaderTest()
	{
		String prodListHeader = prodlistingPage.getProductListHeader();
		System.out.println("Product List Header is:" +prodListHeader);
		Assert.assertEquals(prodListHeader, Constants.PRODUCT_LIST_HEADER);
	}
	
	
	@Description("Testing all the products available on the Product Listing page")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 2)
	public void productListSectionsTest()
	{
		List<String> acutProducts = prodlistingPage.productLists();
		System.out.println("Acutal Products are:" +acutProducts);
		Assert.assertEquals(acutProducts, Constants.EXPECTED_PRODUCTS_LIST);
	}

	@Description("Selecting particular product to add in the Cart")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 3)
	public void selectProdTest()
	{
		proDetailsPage = prodlistingPage.selectProduct("Sauce Labs Bike Light");
		String selProduct = proDetailsPage.getSelectedProd();
		Assert.assertEquals(selProduct, "Sauce Labs Bike Light");
		
	}


	
	
	
	
	

}
