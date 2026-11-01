package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ConfigurableProductPage extends AbstractPage{

    public ConfigurableProductPage(WebDriver driver) {
        super(driver);
    }

    // Dropdowns
    private By processorDropdown = By.id("product_attribute_16_5_4");
    private By ramDropdown = By.id("product_attribute_16_6_5");

    // Radio buttons
    private By hdd400gbRadioButton = By.id("product_attribute_16_3_6_19");
    private By windows10RadioButton = By.id("product_attribute_16_4_7_44");

    // Checkboxes
    private By officeCheckbox = By.id("product_attribute_16_8_8_22");
    private By acrobatCheckbox = By.id("product_attribute_16_8_8_23");
    private By totalCommanderCheckbox = By.id("product_attribute_16_8_8_24");

    private By productTitle = By.xpath("//h1[@itemprop='name']");
    private By addToCartButton = By.xpath("//input[@value='Add to cart']");
    private By successMessage= By.xpath("//div[@id='bar-notification']//p[@class='content']");



    public String getProductTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).getText();
    }

    public void selectProcessor(String optionText) {
        WebElement dropdown = driver.findElement(processorDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(optionText);
    }

    public void selectRamByValue(String value) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(ramDropdown));
        Select select = new Select(dropdown);

        select.selectByValue(value);
    }

    public void selectHdd400Gb() {
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(hdd400gbRadioButton));
        radioButton.click();
    }

    public void selectWindows10() {
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(windows10RadioButton));
        radioButton.click();
    }

    public void selectOffice() {
        wait.until(ExpectedConditions.elementToBeClickable(officeCheckbox)).click();
    }

    public void selectAcrobatReader() {
        wait.until(ExpectedConditions.elementToBeClickable(acrobatCheckbox)).click();
    }

    public void selectTotalCommander() {
        wait.until(ExpectedConditions.elementToBeClickable(totalCommanderCheckbox)).click();
    }

    public void clickAddToCartButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        button.click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }

}
