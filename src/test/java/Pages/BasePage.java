package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends AbstractPage{

    public BasePage(WebDriver driver) {
        super(driver);
    }

    private By cartLinkForHover = By.id("topcartlink");
    private By headerCartQty = By.className("cart-qty");

    private By registerLink = By.xpath("//a[@class='ico-register']");
    private By loginLink = By.xpath("//a[@class='ico-login']");
    private By loggedInUserEmail = By.xpath("(//a[@class='account'])[1]");
    private By searchField = By.xpath("//input[@class='search-box-text ui-autocomplete-input']");
    private By searchButton = By.xpath("//input[@class='button-1 search-box-button']");
    private By apparelShoesMenuLink = By.xpath("//ul[@class='top-menu']//a[normalize-space()='Apparel & Shoes']");
    private By computersMenu = By.xpath("//ul[@class='top-menu']//a[contains(text(),'Computers')]");
    private By desktopsSubMenu = By.xpath("//ul[@class='top-menu']//a[contains(text(),'Desktops')]");

    private By accessoriesSubMenu = By.cssSelector(".top-menu a[href='/accessories']");

    private By flyoutProductQuantity = By.className("quantity");
    private By flyoutItemsCountMessage = By.xpath("//div[@class='count']");
    private By cartLinkForClick = By.xpath("//span[@class='cart-label']");
    private By logoutLink = By.cssSelector(".ico-logout");
    private By wishlistLink = By.xpath("//a[@class='ico-wishlist']");


    public void clickRegisterLink(){
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public String getLoggedInUserEmail() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInUserEmail)).getText();
    }

    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void clickApparelAndShoesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(apparelShoesMenuLink)).click();
    }

    public void hoverComputersAndClickDesktops() {
        WebElement computers = wait.until(ExpectedConditions.visibilityOfElementLocated(computersMenu));

        Actions actions = new Actions(driver);

        actions.moveToElement(computers).perform();

        WebElement desktops = wait.until(ExpectedConditions.elementToBeClickable(desktopsSubMenu));
        desktops.click();

    }

    public void hoverComputersAndClickAccessories() {

        WebElement mainTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(computersMenu));

        Actions actions = new Actions(driver);
        actions.moveToElement(mainTitle).build().perform();

        WebElement accessories = wait.until(ExpectedConditions.elementToBeClickable(accessoriesSubMenu));
        accessories.click();
    }

    public String getFlyoutProductQuantity() {

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(cartLinkForHover));

        Actions actions = new Actions(driver);
        actions.moveToElement(cart).perform();

        return wait.until(ExpectedConditions.visibilityOfElementLocated(flyoutProductQuantity)).getText();
    }

    public String getHeaderCartQty(String expectedQty) {

        wait.until(ExpectedConditions.textToBePresentInElementLocated(headerCartQty, expectedQty));

        return driver.findElement(headerCartQty).getText();

    }

    public String getFlyoutItemsCountMessage() {

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(cartLinkForHover));

        Actions actions = new Actions(driver);
        actions.moveToElement(cart).perform();

        return wait.until(ExpectedConditions.visibilityOfElementLocated(flyoutItemsCountMessage)).getText();
    }

    public void clickHeaderShoppingCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLinkForClick)).click();
    }

    public void clickLogOut() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public void clickHeaderWishList() {
        wait.until(ExpectedConditions.elementToBeClickable(wishlistLink)).click();
    }

}
