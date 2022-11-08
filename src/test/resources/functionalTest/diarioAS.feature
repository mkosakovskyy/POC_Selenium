@as.com

Feature: Feature - Diario AS proof of concept for Prisa

  Background: Navigate to the home page
    Given The user is on the as.com home page
    And The user accepts cookies pop-up

  @Case01 @News
  @severity=blocker
  Scenario: Scenario - Access to a football team news
    When The user access Atletico de Madrid within the Futbol section
    Then The Atletico de Madrid team page is correct
    And The Atletico de Madrid news are displayed
