package com.prisa.poc.pages;

import com.prisa.poc.locators.HeaderLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderPage extends AbstractPage {

    HeaderLocators headerLoc;

    /** Constructor */

    HeaderPage(WebDriver driver) {
        super(driver);
        this.headerLoc = new HeaderLocators();
        PageFactory.initElements(driver, headerLoc);
    }

    /** Actions */

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }

    public void clickMenuAtletico() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(headerLoc.dropdownFutbol).build().perform();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(headerLoc.optionAtletico));
        actions.moveToElement(headerLoc.optionAtletico).click().build().perform();
    }

    public void clickMenuFormulaOne() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(headerLoc.dropdownMotor).build().perform();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(headerLoc.optionFormulaOne));
        actions.moveToElement(headerLoc.optionFormulaOne).click().build().perform();
    }

    public void clickHeaderLogo() {
        headerLoc.logoAS.click();
    }
}