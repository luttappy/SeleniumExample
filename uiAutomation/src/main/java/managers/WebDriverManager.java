package managers;

import enums.DriverType;
import enums.EnvironmentType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class WebDriverManager {
    protected static WebDriver driver;
    private static DriverType driverType;
    private ChromeDriverService service;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public WebDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case REMOTE : driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        final String URL =  FileReaderManager.getInstance().getConfigReader().getGridURL();
        System.out.println("URL is "+URL);
        ChromeOptions options= new ChromeOptions();
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        options.addArguments("--start-maximized");
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        caps.setBrowserName(BrowserType.CHROME);

        try {
            driver = new RemoteWebDriver(new URL(URL), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return driver;
    }

    private WebDriver createLocalDriver() {
        String os = System.getProperty("os.name");
        switch (driverType) {
            case FIREFOX :
                System.setProperty("webdriver.gecko.driver",FileReaderManager.getInstance().getConfigReader().getDriverPath(os));//.concat("/geckodriver.exe"));
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette",true);
                driver= new FirefoxDriver(capabilities);
                break;
            case SAFARI :
                driver = new SafariDriver();
                break;
            case CHROME :
//                System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath(os).concat("/chromedriver"));
                ChromeOptions options= new ChromeOptions();

                DesiredCapabilities caps = DesiredCapabilities.chrome();
                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.BROWSER, Level.ALL);
                caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                caps.setCapability(ChromeOptions.CAPABILITY, options);
                options.addArguments("start-maximized");
                options.addArguments("enable-automation");
//	        options.addArguments("--headless"); // only if you are ACTUALLY running headless
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.50 Safari/537.36");
//	        options.addArguments("--no-sandbox");
//	        options.addArguments("--disable-infobars");
//	        options.addArguments("--disable-dev-shm-usage");
//	        options.addArguments("--disable-browser-side-navigation");
//	        options.addArguments("--disable-gpu");

                driver = new ChromeDriver(options);
                break;
//		case SAFARI : driver = newSafariDriver();
//			break;
//		default:
//			System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath());
//			driver = new ChromeDriver();
//			break;
        }
        if(FileReaderManager.getInstance().getConfigReader().getBrowser() != null) {
            if(os.contains("Windows")) driver.manage().window().maximize();
            else driver.manage().window().fullscreen();
        }
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    public void quitDriver() {
        System.out.println("executed driver quit");
        driver.close();
        driver.quit();

    }
}
