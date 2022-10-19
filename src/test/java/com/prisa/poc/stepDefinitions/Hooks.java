package com.prisa.poc.stepDefinitions;

import com.prisa.poc.pages.PagesFactory;
import com.prisa.poc.utils.Flags;
import com.prisa.poc.utils.ScreenRecorderUtil;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import java.util.concurrent.TimeUnit;

public class Hooks {

    private static WebDriver driver;
    public static final int TIMEOUT = 10;

    /** Delete all cookies at the start of each scenario to avoid shared state between tests */
    @Before
    @SuppressWarnings("deprecation")
    public void setUp() {
        try { ScreenRecorderUtil.startRecord("main"); } catch (Exception e) {}
        String browser = Flags.getInstance().getBrowser();
        if (StringUtils.isBlank(browser)) browser = "chrome";
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                optionsFirefox.addArguments("--headless");
                driver = new FirefoxDriver(optionsFirefox);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            default:
                System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                WebDriverManager.chromedriver().setup();
                ChromeOptions optionsChrome = new ChromeOptions();
                // optionsChrome.addArguments("--headless");
                optionsChrome.addArguments("--no-sandbox");
                optionsChrome.addArguments("--disable-gpu");
                optionsChrome.addArguments("--disable-dev-shm-usage");
                optionsChrome.addArguments("--disable-web-security");
                optionsChrome.addArguments("--enable-javascript");
                optionsChrome.addArguments("--remote-debugging-port=9222");
                optionsChrome.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(optionsChrome);
        }
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(new Dimension(1920,1200));
        PagesFactory.start(driver);
    }

    @AfterStep
    public void capturaaas(Scenario scenario) {
        try {
            final byte[] screenByte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenByte, "image/png", scenario.getName());
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }

    /** Embed a screenshot in test report if test is marked as failed */
    @After
    public void tearDown(Scenario scenario) {
        driver.quit();
        try { ScreenRecorderUtil.stopRecord(); } catch (Exception e) {}
    }

    /** @AfterStep
    public void afterStep(Scenario scenario) {
    ScreenshotTaker.takeScreenshot(scenario);
    } */
}