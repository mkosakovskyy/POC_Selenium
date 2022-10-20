package com.prisa.poc.utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.*;

public class TakeScreenshot {

    public void takeScreen(Scenario scenario, WebDriver driver) {
        try {
            final byte[] screenByte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenByte, "image/png", scenario.getName());
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }
}
