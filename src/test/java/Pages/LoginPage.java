package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage{
//    private WebDriver driver;
//    private WebDriverWait wait;

    // konstruktor
    public LoginPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        super (driver);
    }

    // lokatori
    private By emailInput = By.xpath("//input[@id='Email']");
    private By passwordInput = By.xpath("//input[@id='Password']");
    private By loginButton = By.cssSelector(".button-1.login-button");
    private By loginErrorSummary = By.cssSelector(".validation-summary-errors");
    private By invalidEmailMessage = By.xpath("//span[text()='Please enter a valid email address.']");
    private By incorrectCredentialsMessage = By.xpath("//li[text()='The credentials provided are incorrect']");
    private By accountNotFoundMessage = By.xpath("//li[text()='No customer account found']");

    // metodi i akcii
    //  Method for entering user credentials
    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }


    public boolean isLoginErrorDisplayed() {
        // Враќа true ако има порака за грешка, false ако ја нема
        return driver.findElements(loginErrorSummary).size() > 0;
    }

    public String getInvalidEmailMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(invalidEmailMessage)).getText();
    }

    public String getIncorrectCredentialsMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(incorrectCredentialsMessage)).getText();

    }

    public String getAccountNotFoundMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountNotFoundMessage)).getText();
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

    }

}
