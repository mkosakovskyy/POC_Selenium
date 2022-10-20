package com.prisa.poc.utils;

import com.prisa.poc.pages.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitLoad extends AbstractPage {

    /** Constructor */

    public WaitLoad(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Methods */

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
}