package Base;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected RegisterPage registerPage;
    protected RegisterResultPage registerResultPage;
    protected LoginPage loginPage;
    protected SearchResultsPage searchResultsPage;
    protected ConfigurableProductPage configurableProductPage;
    protected ShoppingCartPage shoppingCartPage;
    protected BasePage basePage;
    protected CheckoutPage checkoutPage;
    protected CheckoutAsGuestPage checkoutAsGuestPage;
    protected ProductDetailsPage productDetailsPage;
    protected WishlistPage wishlistPage;
    protected ProductListingPage productListingPage;


    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");

        registerPage = new RegisterPage(driver);
        registerResultPage = new RegisterResultPage(driver);
        loginPage = new LoginPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        configurableProductPage = new ConfigurableProductPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        basePage = new BasePage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutAsGuestPage = new CheckoutAsGuestPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        wishlistPage = new WishlistPage(driver);
        productListingPage = new ProductListingPage(driver);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
