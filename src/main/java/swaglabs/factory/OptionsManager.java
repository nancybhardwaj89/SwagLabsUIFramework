package swaglabs.factory;

import java.util.Properties;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getchromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))co.addArguments("--headless");
		
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))co.addArguments("--incognito");
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "chrome");
			//co.setBrowserVersion(prop.getProperty("browserversion").trim());
		}
		return co;
	}

	public FirefoxOptions getfirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))co.addArguments("--incognito");
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("browserName", "firefox");
			//fo.setBrowserVersion(prop.getProperty("browserversion").trim());
	}
		return fo;
	}
}
