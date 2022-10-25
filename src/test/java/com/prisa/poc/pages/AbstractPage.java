package com.prisa.poc.pages;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

@Slf4j
public abstract class AbstractPage {

    /** Variables */

    protected Wait<WebDriver> wait;
    private final WebDriver driver;

    /** Constructor */

    AbstractPage(WebDriver driver) {
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

    /* ================= */
    /* Wait/Load methods */
    /* ================= */

    /**
     * Esperar mediante una suspensión temporal del webdriver y controlado con un try/catch
     * @param time : int : Tiempo a esperar en milisegundos
     */
    public void sleepDriver(int time) {
        try { Thread.sleep(time); } catch (InterruptedException e) {}
    }

    /**
     * Esperar a que sea visible cierto elemento
     * En caso de no aparecer en 5 segundos, el caso continua
     * @param elem : WebElement : Elemento a esperar
     */
    public void waitForElementVisible(WebElement elem) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(4)).until(ExpectedConditions.visibilityOf(elem));
        } catch (Exception e) {}
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }

    /**
     * Esperar a que sea pulsable cierto elemento
     * En caso de no serlo en 5 segundos, el caso continua
     * @param elem : WebElement : Elemento a esperar
     */
    public void waitForElementClickable(WebElement elem){
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(elem));
        } catch (Exception e) {}
    }

    /**
     * Esperar a que sea visible el contenedor publicitario del fondo
     * En caso de no aparecer en 20 segundos, el caso continua
     */
    public void waitForAdvertisementVisible() {
        try {
            WebElement elem = getDriver().findElement(By.id("pbnetVideo"));
            new WebDriverWait(getDriver(), Duration.ofSeconds(17)).until(ExpectedConditions.visibilityOf(elem));
        } catch (Exception e) {}
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }

    /**
     * Comprobar si la página esta cargada mediante la existencia de un elemento
     * @param elem : WebElement : Elemento con el que comprobarlo
     * @return Boolean
     */
    public boolean isPageLoaded(WebElement elem) {
        boolean isLoaded = false;
        try {
            isLoaded = elem.isDisplayed();
        } catch (NoSuchElementException e) { e.printStackTrace();}
        return isLoaded;
    }

    /**
     * Comprobar si un elemento esta presente en la página
     * @param elem : WebElement : Elemento a comprobar
     * @return Boolean
     */
    public boolean isElementPresent(WebElement elem) {
        boolean isPresent;
        try {
            isPresent = elem.isDisplayed();
        } catch (NoSuchElementException e) { isPresent = false; }
        return isPresent;
    }

    /**
     * Esperar a que sea visible cierto elemento
     * @param elem : WebElement : Elemento a esperar
     * @param time : int : Tiempo de espera en milisegundos
     */
    public void waitUntilVisible(WebElement elem, int time) {
        new WebDriverWait(getDriver(), Duration.ofMillis(time)).until(ExpectedConditions.visibilityOf(elem));
    }

    /* =================== */
    /* Scroll/Move methods */
    /* =================== */

    public void scrollDown(int pixels){ ((JavascriptExecutor) getDriver()).executeScript("scroll(0, "+pixels+")", ""); }

    public void scrollUp(int pixels){
        ((JavascriptExecutor) getDriver()).executeScript("scroll("+pixels+", 0)", "");
    }

    public void scrollToTop(){ ((JavascriptExecutor) driver).executeScript("scroll(document.body.scrollHeight, 0)", ""); }

    public void scrollTo(WebElement elem) {
        if (((RemoteWebDriver) driver).getCapabilities().getBrowserName().equals("chrome")) {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(elem).build().perform();
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
        }
    }

    public void scrollSearchClick(WebElement elem) {
        try{ elem.click();
        } catch(WebDriverException wde){
            if(wde.getMessage().contains("is not clickable at point")){
                // scrollDown(200);
                // scrollSearchClick(elem);
                scrollTo(elem);
                scrollUp(100);
                scrollSearchClick(elem);
            } else{ wde.printStackTrace(); }
        }
    }

    public void moveTo(WebElement elem) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(elem).build().perform();
    }

    public void moveToAndClick(WebElement elem) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(elem).click().build().perform();
    }

    /* =============== */
    /* General methods */
    /* =============== */

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