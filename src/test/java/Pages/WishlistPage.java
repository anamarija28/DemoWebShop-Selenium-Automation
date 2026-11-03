package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;

public class WishlistPage extends AbstractPage {

    public WishlistPage (WebDriver driver) {
        super(driver);
    }


    private By addToCartCheckboxes = By.name("addtocart");
    private By addToCartButton = By.name("addtocartbutton");
    private By productNameLinks = By.xpath("//table[contains(@class,'cart')]//a[contains(@href,'/')]");


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

    public List<String> getProductNames() {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNameLinks));
        List<String> names = new ArrayList<>();
        for (WebElement element : elements) {
            names.add(element.getText().trim());
        }
        return names;
    }

}
