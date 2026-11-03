package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends AbstractPage{

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    private By addToCartButton = By.cssSelector("input.button-1.add-to-cart-button");
    private By addToWishlistButton = By.cssSelector("input.add-to-wishlist-button");
    private By quantityInput = By.cssSelector("input.qty-input");

    public void clickAddToCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public void clickAddToWishlistButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addToWishlistButton)).click();
    }

    public void setQuantity(String quantity) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        input.clear();
        input.sendKeys(quantity);
    }
}
