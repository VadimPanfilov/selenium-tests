package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class ResultsPage {
    private final ElementsCollection flightRows = $$("#flightsList tbody tr");
    private final SelenideElement sortBySelect = $x("//select[option[contains(text(), 'Времени вылета')]]");
    private final SelenideElement sortOrderSelect = $x("//select[option[contains(text(), 'По возрастанию')]]");

    public void shouldShowResults() {
        flightRows.first().shouldBe(visible);
    }

    public void sortBy(String field, String order) {
        if (field.equals("price")) {
            sortBySelect.selectOptionContainingText("Цене");
        } else {
            sortBySelect.selectOptionContainingText("Времени вылета");
        }
        sortOrderSelect.selectOptionContainingText(order.equals("asc") ? "По возрастанию" : "По убыванию");
    }

    public void shouldBeSortedByPriceAscending() {
        if (flightRows.size() < 2) {
            System.out.println("Найден 1 рейс. Сортировка не требуется.");
            return;
        }
        String firstPriceText = flightRows.get(0).$("td:nth-child(6)").getText();
        String secondPriceText = flightRows.get(1).$("td:nth-child(6)").getText();
        int p1 = Integer.parseInt(firstPriceText.replaceAll("[^0-9]", ""));
        int p2 = Integer.parseInt(secondPriceText.replaceAll("[^0-9]", ""));
        if (p1 > p2) {
            throw new AssertionError("Сортировка по цене не работает! Первая: " + p1 + ", Вторая: " + p2);
        }
    }
    public void shouldShowNoResultsMessage() {
        $("#flightsList").shouldHave(text("Рейсов по вашему запросу не найдено"));
    }
    public void clickFirstFlightRegisterButton() {
        flightRows.first().$x(".//button[contains(text(), 'Зарегистрироваться')]").click();
    }
}


