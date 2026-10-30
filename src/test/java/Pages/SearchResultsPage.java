package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends AbstractPage{

    // konstruktor
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    // lokatori
    private By searchKeywordInput = By.cssSelector(".search-text");


    private By productItem = By.cssSelector(".product-item");
    private By productTitles = By.className("product-title");
    private By addToCart = By.xpath("(//input[@class='button-2 product-box-add-to-cart-button'])[1]");
    private By successMessage = By.xpath("//p[@class='content']");

    // metodi i akcii
    public String getSearchFieldValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchKeywordInput)).getAttribute("value");

    }

    public boolean areProductsDisplayed() {
        return driver.findElements(productItem).size() > 0;
    }

    public List<String> getProductTitles() {
        // 1. Наоѓање на сите елементи (List of WebElements). driver.findElements враќа List<WebElement>
        List<WebElement> elements = driver.findElements(productTitles);

        // 2. Креирање на празна листа за текстовите (List of Strings)
        List<String> titles = new ArrayList<>();

        // 3. For-each циклус
        for (WebElement element : elements) {
            // За секој најден елемент, го земаме текстот и го додаваме во листата 'titles'
            titles.add(element.getText());
        }

        // 4. Враќање на готовата листа со имиња
        return titles;

    }


    public void clickAddToCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
    }

    public String getSuccessMessage () {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }




}
