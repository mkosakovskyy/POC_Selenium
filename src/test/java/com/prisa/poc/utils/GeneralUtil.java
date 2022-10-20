package com.prisa.poc.utils;

import com.prisa.poc.pages.AbstractPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

@Slf4j
public class GeneralUtil extends AbstractPage {

    public GeneralUtil(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getUrl() { return driver.getCurrentUrl(); }

    public void navigateTo(String url) {
        try {
            getDriver().navigate().to(url);
        } catch (java.lang.Exception e) {
            if (e instanceof TimeoutException) {
                log.info("Timeout loading home page");
            } else if (e instanceof ScriptTimeoutException) {
                log.info("Script Timeout loading home page");
            } else {
                log.error(e.getMessage());
            }
        }
    }

    private boolean parseBoolean(String string) {
        String result = string == null ? "false" : string;
        result = result.toLowerCase().trim();
        return (result.equals("true") || result.equals("false")) && Boolean.parseBoolean(result);
    }

    public static void checkEqualsStrings(String[] actual, String[] expected) {
        for (int i = 0; i < actual.length; i++) {
            log.info("Actual: " + actual[i] + " Expected: " + expected[i]);
            assertEquals(actual[i], expected[i], "Incorrect string " + i + " - Actual: " +
                    actual[i] + " Expected: " + expected[i]);
        }
    }

    public static void dragAndDropElement(WebDriver driver, By locator, int xOffset, int yOffset){
        WebElement slider = driver.findElement(locator);
        Actions move = new Actions(driver);
        Action action = move.dragAndDropBy(slider, xOffset, yOffset).build();
        action.perform();
    }

    public void switchWindow() {
        for(String winHandle : getDriver().getWindowHandles()){
            getDriver().switchTo().window(winHandle);
        }
    }
}
