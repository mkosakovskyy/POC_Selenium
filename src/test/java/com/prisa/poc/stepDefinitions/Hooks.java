package com.prisa.poc.stepDefinitions;

import com.prisa.poc.pages.PagesFactory;
import com.prisa.poc.utils.Flags;
import com.prisa.poc.utils.ScreenRecorder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;

public class Hooks {

    /** Variables */
    
    private static WebDriver driver;
    public static final int TIMEOUT = 10;

    /** Delete all cookies at the start of each scenario to avoid shared state between tests */
    @Before
    @SuppressWarnings("deprecation")
    public void setUp() {
        /* Proxy for Docker created in .browserInDocker() */
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("81.171.24.199:3128");
        try {
            ScreenRecorder.startRecord("main");
        } catch (Exception e) {
        }
        String browser = Flags.getInstance().getBrowser();
        if (StringUtils.isBlank(browser)) browser = "chrome";
        switch (browser) {
            case "firefox":
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                optionsFirefox.setCapability(CapabilityType.PROXY, proxy);
                driver = WebDriverManager.firefoxdriver().capabilities(optionsFirefox).browserInDocker().create();
                /* Lines for executing without docker
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                driver = new FirefoxDriver(optionsFirefox);
                 */
                break;
            case "edge":
                EdgeOptions optionsEdge = new EdgeOptions();
                optionsEdge.setCapability(CapabilityType.PROXY, proxy);
                driver = WebDriverManager.edgedriver().capabilities(optionsEdge).browserInDocker().create();
                /* Lines for executing without docker
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                */
                break;
            case "safari":
                SafariOptions optionsSafari = new SafariOptions();
                optionsSafari.setCapability(CapabilityType.PROXY, proxy);
                //driver = WebDriverManager.safaridriver().capabilities(optionsSafari).browserInDocker().create();
                driver = WebDriverManager.safaridriver().capabilities(optionsSafari).create();
                /* Lines for executing without docker
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                */
                break;
            default:
                System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.setCapability(CapabilityType.PROXY, proxy);
                driver = WebDriverManager.chromedriver().capabilities(optionsChrome).browserInDocker().create();
                /* Lines for executing without docker
                WebDriverManager.chromedriver().setup();
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("--no-sandbox");
                optionsChrome.addArguments("--disable-gpu");
                optionsChrome.addArguments("--disable-dev-shm-usage");
                optionsChrome.addArguments("--disable-site-isolation-trials");
                driver = new ChromeDriver(optionsChrome);
                */
        }
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        PagesFactory.start(driver);
    }

    /** Embed a screenshot in test report if test is marked as failed */
    @After
    public void tearDown(Scenario scenario) {
        try {
            final byte[] screenByte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenByte, "image/png", scenario.getName());
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        driver.quit();
        try { ScreenRecorder.stopRecord(); } catch (Exception e) {}
    }

    /** @AfterStep
    public void afterStep(Scenario scenario) {
    ScreenshotTaker.takeScreenshot(scenario);
    } */
}
