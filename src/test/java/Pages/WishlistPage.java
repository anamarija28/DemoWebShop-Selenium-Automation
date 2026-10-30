package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import org.openqa.selenium.WebElement;

public class WishlistPage extends AbstractPage {


    public WishlistPage (WebDriver driver) {
        super(driver);
    }


    private By addToCartCheckboxes = By.name("addtocart");
    private By addToCartButton = By.name("addtocartbutton");

    public void selectAllAddToCartCheckboxes() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartCheckboxes));

        List<WebElement> checkboxes = driver.findElements(addToCartCheckboxes);

        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }
    public void clickAddToCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }
}
