package com.prisa.poc.pages;

import com.prisa.poc.locators.HomeLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends AbstractPage {

    public static final String PAGE_URL = "https://as.com/?nrd=1";
    public static final String PAGE_US_URL = "https://us.as.com/";
    HomeLocators homeLoc;

    /** Constructor */

    HomePage(WebDriver driver) {
        super(driver);
        this.homeLoc = new HomeLocators();
        PageFactory.initElements(driver, homeLoc);
    }

    /** Actions */

    @Override
    public WebElement getPageLoadedTestElement() {
        return homeLoc.titleFirstNews;
    }

    public void clickAcceptCookies() {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(homeLoc.btnAcceptCookies));
            if (isElementPresent(homeLoc.btnAcceptCookies)) {
                homeLoc.btnAcceptCookies.click();
            }
        } catch (NoSuchElementException | NoSuchFrameException e) {}
    }

    public String clickFirstNews() {
        moveTo(homeLoc.titleFirstNews);
        String newsUrl = homeLoc.titleFirstNews.getAttribute("href");
        homeLoc.titleFirstNews.click();
        return newsUrl;
    }

    public void redirectSpain() {
        String currentUrl = getDriver().getCurrentUrl();
        if (currentUrl.equals(PAGE_US_URL)) {
            navigateTo(PAGE_URL);
        }
    }
}
