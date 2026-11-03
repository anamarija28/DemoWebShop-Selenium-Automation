package Tests;
import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;


public class T04_AddToCartTest extends BaseClass {

    String searchTerm = "jeans";

    private void addInstructorLedTrainingToCart() {
        basePage.hoverComputersAndClickAccessories();
        productListingPage.addProductToCart("TCP Instructor Led Training");
    }

    // APPAREL & SHOES
    @Test (description = "Apparel & Shoes")
    public void addToCartThroughSearch() {

        basePage.searchProduct(searchTerm);

        boolean resultsCount = searchResultsPage.areProductsDisplayed();
        Assert.assertTrue(resultsCount, "The products for the searched keyword are not displayed!");

        String actualSuccessMessage = searchResultsPage.addProductToCart("Blue Jeans");
        Assert.assertEquals(actualSuccessMessage, "The product has been added to your shopping cart");
    }

    // COMPUTERS - ACCESSORIES
    @Test
    public void addToCartThroughTopMenu() {

        basePage.hoverComputersAndClickAccessories();

        String actualTitle = productListingPage.getPageTitle();
        Assert.assertEquals(actualTitle, "Accessories", "The title is not Accessories");

        String actualSuccessMessage = productListingPage.addProductToCart("TCP Instructor Led Training");
        Assert.assertEquals(actualSuccessMessage, "The product has been added to your shopping cart");

        basePage.clickHeaderShoppingCart();
        List<String> productsInCart = shoppingCartPage.getAllProductNames();
        Assert.assertTrue(productsInCart.contains("TCP Instructor Led Training"), "The product is not in the shopping cart!");
    }

    @Test
    public void addSameProductTwice() {

        basePage.hoverComputersAndClickAccessories();

        productListingPage.addProductToCart("TCP Instructor Led Training");

        String actualSuccessMessage = productListingPage.addProductToCart("TCP Instructor Led Training");
        Assert.assertEquals(actualSuccessMessage, "The product has been added to your shopping cart");

        String actualHeaderQty = basePage.getHeaderCartQty("(2)");
        Assert.assertEquals(actualHeaderQty, "(2)", "The number in the header is not 2!");

        String actualFlyoutText = basePage.getFlyoutProductQuantity();
        Assert.assertTrue(actualFlyoutText.contains("2"), "In the flyout menu does not write Quantity: 2!. It is: " + actualFlyoutText);
    }

    @Test
    public void addMultipleDifferentProducts() {

        addInstructorLedTrainingToCart();
        productListingPage.addProductToCart("TCP Public Complete");
        productListingPage.addProductToCart("TCP Public MT/AT");
        productListingPage.addProductToCart("TCP Public RPA/TCD");
        productListingPage.addProductToCart("TCP Self-Paced Training");
        productListingPage.addProductToCart("TCP Self-Paced Training additional month");

        String actualHeaderQty = basePage.getHeaderCartQty("(6)");
        Assert.assertEquals(actualHeaderQty, "(6)", "The number in the header is not 6");

        String actualFlyoutText = basePage.getFlyoutItemsCountMessage();
        Assert.assertTrue(actualFlyoutText.contains("6 item(s)"), "The flyout does not show 6 item(s). It is: " + actualFlyoutText);
    }


    // COMPUTERS - DESKTOPS
    @Test
    public void addProductWithSelectingRequiredOptions() {
        basePage.hoverComputersAndClickDesktops();
        String actualDesktopTitle = productListingPage.getPageTitle();
        Assert.assertTrue(actualDesktopTitle.contains("Desktop"), "The title is incorrect");

        productListingPage.selectProductByTitle("Build your own computer");
        String actualProductTitle = configurableProductPage.getProductTitle();
        Assert.assertTrue(actualProductTitle.contains("Build your own computer"), "The product is incorrect");

        configurableProductPage.selectProcessor("2.5 GHz Intel Pentium Dual-Core E2200 [+15.00]");
        configurableProductPage.selectRamByValue("17");

        configurableProductPage.selectHdd400Gb();
        configurableProductPage.selectWindows10();

        configurableProductPage.selectOffice();
        configurableProductPage.selectAcrobatReader();
        configurableProductPage.selectTotalCommander();

        configurableProductPage.clickAddToCartButton();

        String successMsg = configurableProductPage.getSuccessMessage();
        Assert.assertEquals(successMsg, "The product has been added to your shopping cart");

        basePage.clickHeaderShoppingCart();
        List<String> productsInCart = shoppingCartPage.getAllProductNames();
        Assert.assertTrue(productsInCart.contains("Build your own computer"), "The configured product is not in the cart!");
    }
}
