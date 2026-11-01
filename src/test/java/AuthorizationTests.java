import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test01_login_success() {
        WebElement usernameField = driver.findElement(By.cssSelector("[data-test='username']"));
        usernameField.sendKeys("standard_user");
        WebElement passwordField = driver.findElement(By.cssSelector("[data-test='password']"));
        passwordField.sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        assertTrue(title.getText().equals("Products"), "Логин не выполнен! Заголовок страницы не 'Products'");
    }

    @Test
    public void test02_login_wrong_password() {
        WebElement usernameField = driver.findElement(By.cssSelector("[data-test='username']"));
        usernameField.sendKeys("standard_user");
        WebElement passwordField = driver.findElement(By.cssSelector("[data-test='password']"));
        passwordField.sendKeys("wrong_password");

        driver.findElement(By.cssSelector("[data-test='login-button']")).click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));
        assertTrue(errorMessage.getText().contains("Username and password do not match"), "Сообщение об ошибке не появилось!");
    }
    @Test
    public void test03_login_empty_username() {
        WebElement passwordField = driver.findElement(By.cssSelector("[data-test='password']"));
        passwordField.sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));
        assertTrue(error.getText().contains("Username is required"),
                "Не появилось сообщение об обязательном вводе имени пользователя");
    }

    @Test
    public void test04_login_empty_password() {
        WebElement usernameField = driver.findElement(By.cssSelector("[data-test='username']"));
        usernameField.sendKeys("standard_user");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));
        assertTrue(error.getText().contains("Password is required"),
                "Не появилось сообщение об обязательном вводе пароля");
    }

    @Test
    public void test05_login_locked_out_user() {
        WebElement usernameField = driver.findElement(By.cssSelector("[data-test='username']"));
        usernameField.sendKeys("locked_out_user");

        WebElement passwordField = driver.findElement(By.cssSelector("[data-test='password']"));
        passwordField.sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));
        assertTrue(error.getText().contains("Sorry, this user has been locked out"),
                "Не появилось сообщение о блокировке пользователя");
    }
}
