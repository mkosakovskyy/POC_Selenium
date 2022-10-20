package com.prisa.poc.pages;

import com.prisa.poc.utils.MyFluentWait;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Wait;

@Slf4j
public abstract class AbstractPage {

    /** Variables */

    protected Wait<WebDriver> wait;
    public final WebDriver driver;

    /** Constructor */

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        /** wait = new MyFluentWait<>(driver)
                .withTimeout(5, ChronoUnit.SECONDS)
                .pollingEvery(2, ChronoUnit.SECONDS)
                .ignoring(NoSuchElementException.class); */
    }

    /** Methods */

    protected WebDriver getDriver() { return driver; }

    protected Wait<WebDriver> getWait() { return wait; }

    protected void setWait(Wait<WebDriver> wait) { this.wait = wait; }
}