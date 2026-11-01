package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends AbstractPage{

    public ShoppingCartPage (WebDriver driver) {
        super(driver);
    }

    private By pageTitle = By.xpath("//div[@class='page-title']");
    private By cartRows = By.xpath("//tr[@class='cart-item-row']");
    private By emptyCartMessage = By.className("order-summary-content");
    private By productNameLinks = By.className("product-name");
    private By updateCartButton = By.xpath("//input[@value='Update shopping cart']");
    private By productSubtotals = By.cssSelector(".product-subtotal");
    private By cartTotalPrice = By.cssSelector(".product-price.order-total");
    private By termsOfServiceCheckbox = By.xpath("//input[@id='termsofservice' and @type='checkbox']");
    private By checkoutButton = By.xpath("//button[@id='checkout' and @value='checkout']");

    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public int getNumberOfProductsInCart() {
        List<WebElement> products = driver.findElements(cartRows);
        return products.size();
    }

    public String getEmptyCartMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage)).getText();
    }

    public List<String> getAllProductNames() {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNameLinks));
        List<String> names = new ArrayList<>();

        for(WebElement element : elements) {
            names.add(element.getText().trim());
        }
        return names;
    }


    public void clickUpdateCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(updateCartButton)).click();
    }

    private By quantityInputFor(String productName) {
        return By.xpath("//a[text()='" + productName + "']/ancestor::tr//input[@class='qty-input']");
    }

    public void updateQuantityForProduct(String productName, String quantity) {

        WebElement qtyField = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInputFor(productName)));

        qtyField.click();
        qtyField.sendKeys(Keys.CONTROL + "a");
        qtyField.sendKeys(Keys.BACK_SPACE);
        qtyField.sendKeys(quantity);

        clickUpdateCartButton();
        wait.until(ExpectedConditions.stalenessOf(qtyField));
    }

    public String getProductQuantity(String productName) {

        WebElement qtyField = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInputFor(productName)));
        return qtyField.getAttribute("value");
    }

    public void selectRemoveCheckboxForProduct(String productName) {
        String xpath = "//a[text()='" + productName + "']/ancestor::tr//input[@type='checkbox']";
        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }


    public void removeProduct(String productName) {
        selectRemoveCheckboxForProduct(productName);
        clickUpdateCartButton();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//a[text()='" + productName + "']")));
    }


    public double calculateSubtotalsSum() {

        List<WebElement> subtotals = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productSubtotals));

        double sum = 0;
        for (WebElement element : subtotals) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            String text = element.getText().trim();
            if (!text.isEmpty()) {
                String cleanText = text.replaceAll("[^0-9.]", "");
                sum += Double.parseDouble(cleanText);
            }
        }
        return sum;
    }

    public double getCartTotalPrice() {
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartTotalPrice));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", totalElement);

        return Double.parseDouble(totalElement.getText());
    }


    public void acceptTermsAndCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(termsOfServiceCheckbox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

}
