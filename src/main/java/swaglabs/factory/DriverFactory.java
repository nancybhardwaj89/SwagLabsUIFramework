package swaglabs.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class DriverFactory {

	public WebDriver driver;
	public static String highlight;
	private OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	// This method is used to intilize the driver
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);

		highlight = prop.getProperty("highlight");
		System.out.println("Brower Name is:" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			tlDriver.set(new ChromeDriver(optionsManager.getchromeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			tlDriver.set(new FirefoxDriver(optionsManager.getfirefoxOptions()));
		} else {
			System.out.println("Please pass correct browser Name:" + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("URL"));
		return getDriver();
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProperties() {
		Properties prop = null;
		FileInputStream ip = null;

		String env = System.getProperty("env"); // will be passing through maven as mvn clean install -Denv

		try {
			if (env == null) {
				System.out.println("Running on PROD environment:" + env);

				ip = new FileInputStream("./swaglabs.resources/resources/config.properties");
			} else {
				System.out.println("Runnin on the environment:" + env);
				switch (env) {
				case "staging":
					ip = new FileInputStream("./swaglabs.resources/resources/staging.config.properties");
					break;
				default:
					System.out.println("No Env found");
					throw new Exception("NOENVFOUNDEXCEPTION");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			prop = new Properties();
			prop.load(ip);

		} catch (IOException e) {

			e.printStackTrace();
		}
		return prop;
	}

	// Screenshot Method to take a screenshot
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";

		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
