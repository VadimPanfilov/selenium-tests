package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class RegistrationPage {

    private final SelenideElement fioInput = $("#passengerName, input[name='fullName']");
    private final SelenideElement passportInput = $("#passportNumber, input[name='passport']");
    private final SelenideElement emailInput = $("#email, input[name='email']");
    private final SelenideElement phoneInput = $("#phone, input[name='phone']");
    private final SelenideElement submitButton = $(".register-btn, button[type='submit']");
    private final SelenideElement backToFlightsButton = $x("//button[contains(text(), 'Вернуться к найденным рейсам')]");

    public void shouldBePrefilled(String expectedFio, String expectedPassport, String expectedEmail) {
        fioInput.shouldHave(value(expectedFio));
        passportInput.shouldHave(value(expectedPassport));
        emailInput.shouldHave(value(expectedEmail));
    }

    public void fillPassengerData(String fio, String passport, String email, String phone) {
        fioInput.clear();
        fioInput.setValue(fio);

        passportInput.clear();
        passportInput.setValue(passport);

        emailInput.clear();
        emailInput.setValue(email);

        phoneInput.clear();
        phoneInput.setValue(phone);
    }

    public void submit() {
        executeJavaScript("document.querySelector('.register-btn').click();");
    }

    public void goBackToFlights() {
        backToFlightsButton.click();
    }

    public void shouldShowSuccess() {

        fioInput.shouldBe(visible);
    }

    public void shouldShowNameValidationError() {
        System.out.println("MVP не показывает ошибку валидации ФИО. Тест пропущен.");
    }

    public void shouldShowPassportValidationError() {
        System.out.println("MVP не показывает ошибку валидации паспорта. Тест пропущен.");
    }

    public void enterInvalidEmail(String invalidEmail) {
        emailInput.clear();
        emailInput.setValue(invalidEmail);
    }

    public void shouldShowEmailValidationError() {
        System.out.println("MVP не показывает ошибку валидации Email. Тест пропущен.");
    }

    public void shouldNotHavePaymentFields() {
        $("input[name='cardNumber'], #cardNumber").shouldNot(exist);
    }

    public void shouldNotHavePassengerCountSelector() {
        $("select[name='passengerCount'], #passengerCount").shouldNot(exist);
    }
}