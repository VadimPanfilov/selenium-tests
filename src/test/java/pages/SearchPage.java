package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;

public class SearchPage {
    // Точные ID из скриншотов HTML-кода
    private final SelenideElement fromCitySelect = $("#departureCity");
    private final SelenideElement toCitySelect = $("#arrivalCity");

    // Кнопку поиска ищем по тексту "Найти"
    private final SelenideElement searchButton = $x("//button[text()='Найти']");

    // Кнопку выхода ищем по тексту "Logout" (она видна в шапке сайта)
    private final SelenideElement logoutButton = $x("//*[text()='Logout']");

    // Приветствие ищем по тексту, просто проверяя, что он есть на странице
    private final SelenideElement body = $("body");

    // Проверка, что мы попали на страницу поиска (элементы видны)
    public void shouldBeVisible() {
        fromCitySelect.shouldBe(visible);
    }

    // Метод для выполнения поиска
    public void searchFlight(String fromCity, String toCity) {
        // Выбираем города из выпадающих списков
        fromCitySelect.selectOptionContainingText(fromCity);
        toCitySelect.selectOptionContainingText(toCity);

        // Нажимаем кнопку "Найти"
        searchButton.click();
    }

    // Метод для выхода из системы
    public void logout() {
        logoutButton.click();
    }

    // Проверка приветственного сообщения с ФИО
    public void shouldShowWelcome(String userName) {
        body.shouldHave(text(userName));
    }
}