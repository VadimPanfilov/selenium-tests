package tests;

import org.junit.jupiter.api.Test;
import pages.AgilewayLoginPage;
import pages.FlightSelectionPage;

public class AgilewayTests {

    @Test
    public void userCanLoginAndSelectOneWayFlight() {
        AgilewayLoginPage loginPage = new AgilewayLoginPage().open();
        loginPage.loginAs("agileway", "test$W1se");
        loginPage.shouldShowWelcome("agileway");

        FlightSelectionPage flightPage = new FlightSelectionPage();
        flightPage.selectOneWayTrip();
        flightPage.selectCities("Sydney", "New York");
        flightPage.clickContinue();

        flightPage.shouldShowRoute("Sydney to New York");
    }
}