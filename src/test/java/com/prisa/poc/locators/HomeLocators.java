package com.prisa.poc.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeLocators {

    /** Locators */

    @FindBy(id = "didomi-notice-agree-button")
    public WebElement btnAcceptCookies;

    @FindBy(css = "article h2.s__tl a")
    public WebElement titleFirstNews;
}
