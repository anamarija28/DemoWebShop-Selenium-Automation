package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends AbstractPage{

    // konstruktor
    public RegisterPage (WebDriver driver) {
        super(driver);
    }

    // lokatori
    private By maleGenderRadio = By.xpath("//input[@id='gender-male']");
    private By firstNameInput = By.xpath("//input[@id='FirstName']");
    private By lastNameInput = By.xpath("//input[@id='LastName']");
    private By emailInput = By.xpath("//input[@id='Email']");
    private By passwordInput = By.xpath("//input[@id='Password']");
    private By confirmPasswordInput = By.xpath("//input[@id='ConfirmPassword']");
    private By registerButton = By.xpath("//input[@id='register-button']");

    private By registrationErrorSummary = By.xpath("//div[@class='validation-summary-errors']");

    private By requiredFirstName = By.xpath("//span[@data-valmsg-for='FirstName']");
    private By requiredLastName = By.xpath("//span[@data-valmsg-for='LastName']");
    private By emailErrorMessage = By.xpath("//span[@data-valmsg-for='Email']");
    private By requiredPassword = By.xpath("//span[@data-valmsg-for='Password']");
    private By confirmPasswordError  = By.xpath("//span[@data-valmsg-for='ConfirmPassword']");
    private By passwordLength = By.xpath("//span[text()='The password should have at least 6 characters.']");

    // metodi i akcii
    public void register(String firstName, String lastName, String email, String password, String confirmPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(maleGenderRadio)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordInput)).sendKeys(confirmPassword);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton)).click();
        clickRegisterButton();
    }

    public void clickRegisterButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton)).click();
    }

    public String getRegistrationErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(registrationErrorSummary)).getText();
    }


    public String getFirstNameError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredFirstName)).getText();
    }

    public String getLastNameError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredLastName)).getText();
    }

    public String getEmailError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailErrorMessage)).getText();
    }

    public String getPasswordError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredPassword)).getText();
    }

    public String getConfirmPasswordError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordError)).getText();
    }

//    public String getInvalidEmailError(){
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailErrorMessage)).getText();
//    }

//    public String getPasswordMismatchError(){
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordError)).getText();
//    }

    public String getPasswordLengthValidation(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLength)).getText();
    }

}
