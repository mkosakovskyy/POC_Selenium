package com.prisa.poc.pages;

import com.prisa.poc.locators.HomeLocators;
import com.prisa.poc.utils.GeneralUtil;
import com.prisa.poc.utils.ScrollMove;
import com.prisa.poc.utils.WaitLoad;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {

    /** Variables */

    public static final String PAGE_URL = "https://as.com/?nrd=1";
    public static final String PAGE_US_URL = "https://us.as.com/";

    HomeLocators homeLoc;
    ScrollMove moveUtil = new ScrollMove(getDriver());
    WaitLoad waitUtil = new WaitLoad(getDriver());
    GeneralUtil generalUtil = new GeneralUtil(getDriver());

    /** Constructor */

    HomePage(WebDriver driver) {
        super(driver);
        this.homeLoc = new HomeLocators();
        PageFactory.initElements(driver, homeLoc);
    }

    /** Methods */

    public void waitForPageLoad() { waitUtil.waitForElementVisible(homeLoc.titleFirstNews); }

    public void clickAcceptCookies() {
        try {
            waitUtil.waitUntilVisible(homeLoc.btnAcceptCookies,1000);
            if (waitUtil.isElementPresent(homeLoc.btnAcceptCookies)) homeLoc.btnAcceptCookies.click();
        } catch (NoSuchElementException | NoSuchFrameException e) {}
    }

    public String clickFirstNews() {
        moveUtil.scrollSearchClick(homeLoc.titleFirstNews);
        String newsUrl = homeLoc.titleFirstNews.getAttribute("href");
        homeLoc.titleFirstNews.click();
        return newsUrl;
    }

    public void redirectSpain() {
        String currentUrl = getDriver().getCurrentUrl();
        if (currentUrl.equals(PAGE_US_URL)) generalUtil.navigateTo(PAGE_URL);
    }
}
