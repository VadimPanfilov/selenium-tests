package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("input[type='password']");
    private final SelenideElement loginButton = $x("//button[text()='Войти']");
    private final SelenideElement errorMessage = $("#message, .message, .error-message");

    public LoginPage open() {
        Selenide.open("https://slqamsk.github.io/cases/slflights/v01/");
        return this;
    }

    public void loginAs(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
    }

    public void shouldShowError(String expectedMessage) {
        errorMessage.shouldBe(visible);
    }

    public void shouldBeLoggedIn() {
        usernameInput.shouldBe(hidden);
    }
    public void shouldBeVisible() {
        usernameInput.shouldBe(visible);
    }
    public void shouldHaveErrorMessage(String expectedText) {
        $(".error-message, #errorMessage, .message").shouldHave(text(expectedText));
    }
}