package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class ProductListingPage extends AbstractPage {

    public ProductListingPage(WebDriver driver) {
        super(driver);
    }

    private By notificationBar = By.id("bar-notification");
    private By notificationCloseButton = By.cssSelector("#bar-notification .close");

    private By pageTitle = By.cssSelector(".page-title");
    private By productNameLinks = By.cssSelector(".product-title");
    private String productLinkByTitleXpath = "//h2[@class='product-title']/a[normalize-space()=\"%s\"]";
    private String addToCartButtonByTitleXpath = "//a[text()=\"%s\"]/ancestor::div[@class='details']//input[@value='Add to cart']";
    private By successMessage= By.xpath("//div[@id='bar-notification' and contains(@class,'success')]//p[@class='content']");

    private By sortByDropdown = By.id("products-orderby");
    private By productPrices = By.cssSelector(".prices");
    private By productsPerPageDropdown = By.id("products-pagesize");
    private By viewModeDropdown = By.id("products-viewmode");

    private By gridView = By.cssSelector(".product-grid");
    private By listView = By.cssSelector(".product-list");

    private By currentPageNumber = By.cssSelector(".current-page");
    private By nextPageButton = By.cssSelector(".next-page");
    private By previousPageButton = By.cssSelector(".previous-page");

    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public boolean areProductsDisplayed() {
        return !driver.findElements(productNameLinks).isEmpty();
    }

    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();

        for (WebElement element : driver.findElements(productNameLinks)) {
            names.add(element.getText().trim());
        }

        return names;
    }

    public void selectProductByTitle(String productTitleName) {
        String finalXpath = String.format(productLinkByTitleXpath, productTitleName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(finalXpath))).click();
    }


    public String addProductToCart(String productTitle) {

        String finalXpath = String.format(addToCartButtonByTitleXpath, productTitle);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(finalXpath))).click();

        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();

        wait.until(ExpectedConditions.elementToBeClickable(notificationCloseButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(notificationBar));

        return message;

    }

    public void selectSortOption(String optionText) {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sortByDropdown));

        Select select = new Select(dropdownElement);

        select.selectByVisibleText(optionText);
    }


    public List<Double> getProductPrices() {
        List<WebElement> priceElements = driver.findElements(productPrices);

        // Create an empty list to store only the numeric values (prices as Double).
        // We need this list because we cannot perform mathematical operations (such as sorting) on WebElements,
        // but we can perform them on Double values.
        List<Double> prices = new ArrayList<>();

        for (WebElement el : priceElements) {
            String text = el.getText().replaceAll("[^0-9.]", "");

            // This checks whether the text variable contains any characters after applying
            // the replaceAll cleaning filter.
            // If the text is NOT empty, then do the following:
            if (!text.isEmpty()) {
                prices.add(Double.parseDouble(text));
            }
        }
        return prices;
    }

    public void selectDisplayPerPageOptions(String pageSize) {
        WebElement pageSizeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(productsPerPageDropdown));

        Select select = new Select(pageSizeDropdown);
        select.selectByVisibleText(pageSize);
    }

    public int getNumberOfProductsDisplayed() {
        return driver.findElements(productNameLinks).size();
    }

    public void selectViewMode(String optionText) {
        WebElement pageSizeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(viewModeDropdown));

        Select select = new Select(pageSizeDropdown);
        select.selectByVisibleText(optionText);
    }

    public boolean isGridViewDisplayed() {
        return !driver.findElements(gridView).isEmpty();
    }

    public boolean isListViewDisplayed() {
        return !driver.findElements(listView).isEmpty();
    }


    public void clickPageNumber(String pageNumber) {
        By dynamicPage = By.xpath("//li[@class='individual-page']//a[text()='" + pageNumber + "']");
        wait.until(ExpectedConditions.elementToBeClickable(dynamicPage)).click();
    }

    public String getCurrentPageNumber() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(currentPageNumber)).getText();
    }

    public void goToLastPage() {
        while(!driver.findElements(nextPageButton).isEmpty()) {
            WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));
            next.click();

            wait.until(ExpectedConditions.stalenessOf(next));
        }
    }

    public void goToFirstPage() {
        while(!driver.findElements(previousPageButton).isEmpty()) {
            WebElement previous = wait.until(ExpectedConditions.elementToBeClickable(previousPageButton));
            previous.click();

            wait.until(ExpectedConditions.stalenessOf(previous));
        }
    }

    public boolean isNextPageButtonDisplayed() {
        return !driver.findElements(nextPageButton).isEmpty();
    }

    public boolean isPreviousPageButtonDisplayed() {
        return !driver.findElements(previousPageButton).isEmpty();
    }

}
