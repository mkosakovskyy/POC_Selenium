package com.prisa.poc.pages;

import com.prisa.poc.locators.NewsLocators;
import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class NewsPage extends AbstractPage {

    public static final String ATLETICO_URL = "https://as.com/noticias/atletico-madrid/?omnil=mpal";
    public static final String FORMULA_URL = "https://as.com/motor/formula_1/?omnil=mpal";
    public static final String FACEBOOK_URL = "https://www.facebook.com/";
    NewsLocators newsLoc;

    /** Constructor */

    NewsPage(WebDriver driver) {
        super(driver);
        this.newsLoc = new NewsLocators();
        PageFactory.initElements(driver, newsLoc);
    }

    /** Actions */

    @Override
    public WebElement getPageLoadedTestElement() {
        return newsLoc.eFirstNews;
    }

    public boolean areNewsDisplayed() { return isElementPresent(newsLoc.eFirstNews); }

    public void clickFacebook() { newsLoc.btnFacebook.click(); }

    public boolean areAdvertisementDisplayed(DataTable table) {
        boolean isPresent = false;
        try {
            for(int i = 0; i < table.asList(String.class).size(); ++i) {
                WebElement advertisement = getDriver().findElement(By.id(table.asList().get(i)));
                if (!advertisement.isDisplayed()) {
                    log.info("No se ha encontrado: " + table.asList().get(i));
                    isPresent = false;
                    break;
                }
                if (advertisement.isDisplayed()) {
                    isPresent = true;
                }
            }
        } catch (NoSuchElementException e) { isPresent = false; }
        return isPresent;
    }
}
