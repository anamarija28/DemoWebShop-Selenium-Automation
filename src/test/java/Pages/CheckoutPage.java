package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends AbstractPage{

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private By pageTitle = By.xpath("//div[@class='page-title']");

    private By billingFirstNameInput = By.xpath("//input[@class='text-box single-line' and @id='BillingNewAddress_FirstName']");
    private By billingLastNameInput = By.xpath("//input[@class='text-box single-line' and @id='BillingNewAddress_LastName']");
    private By billingEmailInput = By.xpath("//input[@class='text-box single-line' and @id='BillingNewAddress_Email']");
    private By billingCountryDropdown = By.xpath("//select[@id='BillingNewAddress_CountryId' and @name='BillingNewAddress.CountryId']");
    private By billingCityInput = By.xpath("//input[@id='BillingNewAddress_City' and @name='BillingNewAddress.City']");
    private By billingAddress1Input = By.xpath("//input[@id='BillingNewAddress_Address1' and @name='BillingNewAddress.Address1']");
    private By billingZipPostalCodeInput = By.xpath("//input[@id='BillingNewAddress_ZipPostalCode' and @name='BillingNewAddress.ZipPostalCode']");
    private By billingPhoneNumberInput = By.xpath("//input[@id='BillingNewAddress_PhoneNumber' and @name='BillingNewAddress.PhoneNumber']");
    private By billingContinueButton = By.xpath("//input[@type='button' and @title='Continue']");
    private By shippingAddressDropdown = By.xpath("//select[@id='shipping-address-select' and @name='shipping_address_id']");
    private By billingAddressContinueButton = By.xpath("(//input[@class='button-1 new-address-next-step-button' and @title='Continue'])[1]");
    private By shippingAddressContinueButton = By.xpath("(//input[@class='button-1 new-address-next-step-button' and @title='Continue'])[2]");
    private By secondDayAirRadio = By.xpath("//input[@id='shippingoption_2']");
    private By shippingMethodContinueButton = By.xpath("//input[@class='button-1 shipping-method-next-step-button']");
    private By paymentMethodContinueButton = By.xpath("//input[@class='button-1 payment-method-next-step-button']");
    private By paymentInfoContinueButton = By.xpath("//input[@class='button-1 payment-info-next-step-button']");
    private By orderSuccessMessage = By.xpath("//strong[text()='Your order has been successfully processed!']");
    private By creditCardRadioButton = By.xpath("//input[@id='paymentmethod_2']");
    private By creditCardTypeDropdown = By.xpath("//select[@id='CreditCardType']");
    private By cardHolderNameInput = By.xpath("//input[@id='CardholderName']");
    private By cardNumberInput = By.xpath("//input[@id='CardNumber']");
    private By expiryMonthDropdown = By.xpath("//select[@id='ExpireMonth']");
    private By expiryYearDropdown = By.xpath("//select[@id='ExpireYear']");
    private By cardCodeInput = By.xpath("//input[@id='CardCode']");
    private By orderNumberText = By.cssSelector("ul.details li");

    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }


    public String getBillingFirstName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(billingFirstNameInput)).getAttribute("value");
    }

    public String getBillingLastName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(billingLastNameInput)).getAttribute("value");
    }

    public String getBillingEmail() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailInput)).getAttribute("value");
    }

    public void fillOutBillingAddress(String firstName, String lastName, String email, String country, String city, String address1, String zip, String phone) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(billingFirstNameInput)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingLastNameInput)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailInput)).sendKeys(email);


        // Селектирање од Dropdown
        Select selectCountry = new Select(driver.findElement(billingCountryDropdown));
        selectCountry.selectByVisibleText(country);

        // Пополнување на останатите полиња
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityInput)).sendKeys(city);
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingAddress1Input)).sendKeys(address1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingZipPostalCodeInput)).sendKeys(zip);
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingPhoneNumberInput)).sendKeys(phone);


        // Клик на Continue
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingContinueButton)).click();
    }

    public void fillOutShippingAddress(String shippingAddress) {
        Select selectShippingAddress = new Select(driver.findElement(shippingAddressDropdown));
        selectShippingAddress.selectByVisibleText(shippingAddress);
    }

    public void clickBillingAddressContinueButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingAddressContinueButton)).click();
    }

    public void clickShippingAddressContinueButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(shippingAddressContinueButton)).click();
    }

    public void selectShippingMethod() {
        // Прво чекаме да биде видливо, бидејќи се појавува со анимација
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(secondDayAirRadio));

        // Ако не е веќе селектирано, тогаш кликни го
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    public void clickContinueShippingMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinueButton)).click();
    }

    public void clickPaymentMethodContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinueButton)).click();
    }

    public void clickPaymentInfoContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(paymentInfoContinueButton)).click();
    }

    public void clickConfirmOrderButton() {
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".confirm-order-next-step-button")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmBtn);
        confirmBtn.click();
    }

    public String getOrderSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage)).getText();
    }

    public void fillOutBillingAddressForLoggedInUser(String country, String city, String address, String zipCode, String phoneNumber){

        Select selectCountry = new Select(driver.findElement(billingCountryDropdown));
        selectCountry.selectByVisibleText(country);
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityInput)).sendKeys(city);
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingAddress1Input)).sendKeys(address);
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingZipPostalCodeInput)).sendKeys(zipCode);
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingPhoneNumberInput)).sendKeys(phoneNumber);


        // Клик на Continue
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingContinueButton)).click();
    }

    public void selectPaymentMethod() {
        // Прво чекаме да биде видливо, бидејќи се појавува со анимација
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(creditCardRadioButton));

        // Ако не е веќе селектирано, тогаш кликни го
        if (!radioButton.isSelected()) {
            radioButton.click();
        }

    }
    public void fillOutPaymentInformation(String creditCardType, String cardHolderName, String cardNumberName, String expirationDateMonth, String expirationDateYear, String cardCodeName) {

        WebElement creditCardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(creditCardTypeDropdown));

        Select selectCreditCard = new Select(creditCardElement);
        selectCreditCard.selectByVisibleText(creditCardType);

        wait.until(ExpectedConditions.visibilityOfElementLocated(cardHolderNameInput)).sendKeys(cardHolderName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberInput)).sendKeys(cardNumberName);

        Select selectExpiryDateMonth = new Select(driver.findElement(expiryMonthDropdown));
        selectExpiryDateMonth.selectByVisibleText(expirationDateMonth);

        Select selectExpiryDateYear = new Select(driver.findElement(expiryYearDropdown));
        selectExpiryDateYear.selectByVisibleText(expirationDateYear);

        wait.until(ExpectedConditions.visibilityOfElementLocated(cardCodeInput)).sendKeys(cardCodeName);
    }

    public String getOrderNumberText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberText)).getText();
    }


}