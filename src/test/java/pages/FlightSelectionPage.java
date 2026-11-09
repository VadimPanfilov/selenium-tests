package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class FlightSelectionPage {
    private final SelenideElement oneWayRadio = $("input[value='oneway']");
    private final SelenideElement fromPortSelect = $("select[name='fromPort']");
    private final SelenideElement toPortSelect = $("select[name='toPort']");
    private final SelenideElement continueButton = $("input[value='Continue']");

    public void selectOneWayTrip() {
        oneWayRadio.click();
    }

    public void selectCities(String from, String to) {
        fromPortSelect.selectOptionContainingText(from);
        toPortSelect.selectOptionContainingText(to);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void shouldShowRoute(String expectedRoute) {
        // Проверяем, что на странице появился нужный маршрут
        $("body").shouldHave(text(expectedRoute));
    }
}
