package com.prisa.poc.utils;

import com.prisa.poc.pages.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class ScrollMove extends AbstractPage {

    /** Constructor */

    public ScrollMove(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Methods */

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
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", elem);
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
}