package com.prisa.poc.pages;

import com.prisa.poc.locators.HeaderLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends AbstractPage {

    /** Variables */

    HeaderLocators headerLoc;

    /** Constructor */

    HeaderPage(WebDriver driver) {
        super(driver);
        this.headerLoc = new HeaderLocators();
        PageFactory.initElements(driver, headerLoc);
    }

    /** Methods */

    public void clickHeaderLogo() {
        headerLoc.logoAS.click();
    }

    public void clickMenuAtletico() {
        moveUtil.moveTo(headerLoc.dropdownFutbol);
        waitUtil.sleepDriver(1000);
        moveUtil.moveToAndClick(headerLoc.optionAtletico);
    }

    public void clickMenuFormulaOne() {
        moveUtil.moveTo(headerLoc.dropdownMotor);
        waitUtil.sleepDriver(1000);
        moveUtil.moveToAndClick(headerLoc.optionFormulaOne);
    }
}