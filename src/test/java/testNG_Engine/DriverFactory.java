package testNG_Engine;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import betterplace.utilities.ReadConfig;

/**
 *
 *
 */
public class DriverFactory {

	ReadConfig readconfig = new ReadConfig();
	
    public String pageURL = readconfig.getApplicationURL();
    public static WebDriver driver;
    
    public static Logger logger;
    
    
    @SuppressWarnings("deprecation")
	@Parameters({"browser", "pageURL"})
    @BeforeClass
    public void setup(String browser, String pageurl) {
       	logger = Logger.getLogger("betterPlace");
    	PropertyConfigurator.configure("log4j.properties");

    	if(browser.equals("chrome")) {
    		
    		Map<String, Object> deviceMetrics = new HashMap<>();
        	deviceMetrics.put("width", 400);
        	
        	Map<String, Object> mobileEmulation = new HashMap<>();
        	mobileEmulation.put("deviceMetrics", deviceMetrics);
        	mobileEmulation.put("userAgent", 
        			"Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) "
        			+ "AppleWebKit/535.19 (KHTML, like Gecko) "
        			+ "Chrome/18.0.1025.166 Mobile Safari/535.19");
    		
    		System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
    		ChromeOptions chromeOptions = new ChromeOptions();
    		Map<String, Object> prefs = new HashMap<String, Object>();
    		prefs.put("download.default_directory", readconfig.getDownloadsPath());
    		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
    		chromeOptions.setExperimentalOption("prefs", prefs);
    		//options.addArguments("start-maximized");
    		chromeOptions.addArguments("--disable-popup-blocking");
    		DesiredCapabilities capabilities = DesiredCapabilities.chrome(); 
    		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    		driver = new ChromeDriver(capabilities);
    		
    	} else if(browser.equals("firefox")) {
    		
    		System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
    		driver = new FirefoxDriver();
    	}
    	
    	
    	driver.get(pageurl);
    	
    }
    
    @AfterTest
    public void tarDown() {
    	driver.quit();
    }

    
	public static WebDriver getDriver() {
		return driver;
	}
    
    
    
    
    
}
