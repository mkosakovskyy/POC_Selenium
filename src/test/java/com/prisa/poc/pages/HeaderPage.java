package com.prisa.poc.pages;

import com.prisa.poc.locators.HeaderLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends AbstractPage {

    HeaderLocators headerLoc;

    /** Constructor */

    HeaderPage(WebDriver driver) {
        super(driver);
        this.headerLoc = new HeaderLocators();
        PageFactory.initElements(driver, headerLoc);
    }

    /** Actions */

    public void clickMenuAtletico() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(headerLoc.dropdownFutbol).build().perform();
        try { Thread.sleep(1000); } catch (InterruptedException e) { throw new RuntimeException(e); }
        actions.moveToElement(headerLoc.optionAtletico).click().build().perform();
    }

    public void clickMenuFormulaOne() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(headerLoc.dropdownMotor).build().perform();
        try { Thread.sleep(1000); } catch (InterruptedException e) { throw new RuntimeException(e); }
        actions.moveToElement(headerLoc.optionFormulaOne).click().build().perform();
    }

    public void clickHeaderLogo() {
        headerLoc.logoAS.click();
    }
}