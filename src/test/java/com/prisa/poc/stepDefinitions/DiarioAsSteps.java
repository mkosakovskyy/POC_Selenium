package com.prisa.poc.stepDefinitions;

import com.prisa.poc.pages.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class DiarioAsSteps {

    /** Variables */

    PagesFactory pf = PagesFactory.getInstance();
    HomePage homePage = pf.getHomePage();
    NewsPage newsPage = pf.getNewsPage();
    HeaderPage headerPage = pf.getHeaderPage();
    private String firstNewsURL;

    /** Steps */

    @Given("The user is on the as.com home page")
    public void theUserIsOnTheLoginPage() {
        generalUtil.navigateTo(HomePage.PAGE_URL);
    }

    @And("The user accepts cookies pop-up")
    public void theUserAcceptsCookies() {
        homePage.clickAcceptCookies();
        homePage.waitForPageLoad();
    }

    @When("The user access Atletico de Madrid within the Futbol section")
    public void theUserAccessAtleticoDeMadrid() { headerPage.clickMenuAtletico(); }

    @Then("The Atletico de Madrid team page is correct")
    public void theAtleticoPageIsCorrect() {
        waitUtil.waitForAdvertisementVisible();
        moveUtil.scrollDown(350);
        Assert.assertEquals("El usuario no se encuentra en la página de noticias del Atletico de Madrid", NewsPage.ATLETICO_URL, generalUtil.getUrl());
    }

    @And("The Atletico de Madrid news are displayed")
    public void theAtleticoNewsAreDisplayed() {
        Assert.assertTrue("No se han encontrado las noticias del Atletico de Madrid", newsPage.areNewsDisplayed());
    }

    @And("The user clicks on the banner AS logo")
    public void theUserClicksOnTheBannerASLogo() {
        newsPage.waitForPageLoad();
        headerPage.clickHeaderLogo();
    }

    @Then("The user is redirected to the home page")
    public void theUserIsRedirectedToTheHomePage() {
        // Redirecciona a Latino US
        //homePage.redirectSpain();
        waitUtil.waitForAdvertisementVisible();
        moveUtil.scrollDown(350);
        //Assert.assertEquals("El usuario no se encuentra en la página de inicio", HomePage.PAGE_URL, generalUtil.getUrl());
    }

    @When("The user clicks on the title of the first news")
    public void theUserClicksOnTheTitleOfTheFirstNews() { firstNewsURL = homePage.clickFirstNews(); }

    @Then("The user is on the selected news page")
    public void theUserIsOnTheSelectedNewsPage() {
        newsPage.waitForPageLoad();
        Assert.assertEquals("El usuario no se encuentra en la página de la primera noticia", firstNewsURL, generalUtil.getUrl());
    }

    @When("The user clicks the Facebook icon")
    public void theUserClicksTheFacebookIcon() {
        newsPage.clickFacebook();
    }

    @Then("The Facebook share window is displayed")
    public void theFacebookShareWindowIsDisplayed() {
        generalUtil.switchWindow();
        Assert.assertTrue("El usuario no se encuentra en la ventana de Facebook", generalUtil.getUrl().contains(NewsPage.FACEBOOK_URL));
    }

    @When("The user access Formula One within the Motor section")
    public void theUserAccessMotorFormulaOne() { headerPage.clickMenuFormulaOne(); }

    @Then("The Formula One league page is correct")
    public void theFormulaLeaguePageIsCorrect() {
        waitUtil.waitForAdvertisementVisible();
        moveUtil.scrollDown(350);
        Assert.assertEquals("El usuario no se encuentra en la página de noticias de la Formula Uno", NewsPage.FORMULA_URL, generalUtil.getUrl());
    }

    @And("The Formula One league advertisement elements are displayed")
    public void theFormulaLeagueAdvertisementAreDisplayed(DataTable table) {
        Assert.assertTrue("No se han encontrado los anuncios indicados de Formula Uno", newsPage.areAdvertisementDisplayed(table));
    }
}
