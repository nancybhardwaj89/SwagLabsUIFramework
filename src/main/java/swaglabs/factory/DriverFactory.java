package swaglabs.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.net.URL;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	public WebDriver driver;
	public static String highlight;
	private OptionsManager optionsManager;
	Properties prop;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

// This method is used to intilize the driver
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		System.out.println("Brower Name is:" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver(browserName);
			} else {
				tlDriver.set(new ChromeDriver(optionsManager.getchromeOptions()));
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox");
			} else {
				tlDriver.set(new FirefoxDriver(optionsManager.getfirefoxOptions()));
			}
		} else {
			System.out.println("Please pass correct browser Name:" + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("URL"));
		return getDriver();
	}

	private void init_remoteDriver(String browserName) {
		System.out.println("Running test on remote browser:" + browserName);
		try {
			switch (browserName.toLowerCase().trim()) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getchromeOptions()));
				break;
			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getfirefoxOptions()));
				break;
			default:
				System.out.println("wrong browser info..can not run on grid remote machine....");
				break;
			}
		} catch (MalformedURLException e) {
		}
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProperties() {
		FileInputStream ip = null;
		prop = new Properties();
		String env = System.getProperty("env"); // will be passing through maven as mvn clean install -Denv
		try {
			if (env == null) {
				System.out.println("Running on PROD environment as no envrionment is specified");

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
			// prop = new Properties();
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

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
