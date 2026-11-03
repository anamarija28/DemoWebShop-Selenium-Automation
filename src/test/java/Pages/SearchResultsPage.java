package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResultsPage extends ProductListingPage{

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    private By searchKeywordInput = By.cssSelector(".search-text");

    public String getSearchFieldValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchKeywordInput)).getAttribute("value");
    }

    public boolean isUrlContaining(String text) {
        return wait.until(ExpectedConditions.urlContains(text));
    }

}
