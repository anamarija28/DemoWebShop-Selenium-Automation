package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutAsGuestPage extends AbstractPage{

    public CheckoutAsGuestPage(WebDriver driver) {
        super(driver);
    }

    private By checkoutAsGuestButton = By.xpath("//input[@class='button-1 checkout-as-guest-button']");


    public void clickCheckoutAsGuest() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutAsGuestButton)).click();
    }

}
