package com.prisa.poc.pages;

import com.prisa.poc.locators.HomeLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {

    /** Variables */

    public static final String PAGE_URL = "https://as.com/?nrd=1";
    public static final String PAGE_US_URL = "https://us.as.com/";
    HomeLocators homeLoc;

    /** Constructor */

    HomePage(WebDriver driver) {
        super(driver);
        this.homeLoc = new HomeLocators();
        PageFactory.initElements(driver, homeLoc);
    }

    /** Methods */

    public void waitForPageLoad() { try { waitForElementVisible(homeLoc.titleFirstNews); } catch (Exception e) {} }

    public void clickAcceptCookies() {
        try {
            waitUntilVisible(homeLoc.btnAcceptCookies,1000);
            sleepDriver(1000);
            if (isElementPresent(homeLoc.btnAcceptCookies)) homeLoc.btnAcceptCookies.click();
        } catch (Exception e) {}
    }

    public String clickFirstNews() {
        //scrollTo(homeLoc.titleFirstNews);
        scrollDown(1000);
        String newsUrl = homeLoc.titleFirstNews.getAttribute("href");
        homeLoc.titleFirstNews.click();
        return newsUrl;
    }

    public void redirectSpain() {
        sleepDriver(1000);
        String currentUrl = getDriver().getCurrentUrl();
        if (currentUrl.equals(PAGE_US_URL)) navigateTo(PAGE_URL);
    }
}
