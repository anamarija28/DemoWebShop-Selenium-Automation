package Tests;

import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;


public class T04_AddToCartTest extends BaseClass {

    String searchTerm = "jeans";


    public void addTcpCoachingDayToCart() {
        basePage.hoverComputersAndClickAccessories();
        productListingPage.addProductToCart("TCP Instructor Led Training");
    }

    // APPAREL & SHOES
    @Test (description = "Apparel & Shoes")
    public void addToCartThroughSearch() {

        basePage.searchProduct(searchTerm);

        // URL validation
        boolean isUrlCorrect = searchResultsPage.isUrlContaining("q=" + searchTerm);
        Assert.assertTrue(isUrlCorrect, "The URL does not contain the search term");

        // Validation that the results are displayed
        boolean resultsCount = searchResultsPage.areProductsDisplayed();
        Assert.assertTrue(resultsCount, "The products for the searched keyword are not displayed!");

        // Validation of the search field
        String actualProduct = searchResultsPage.getSearchFieldValue();
        Assert.assertEquals(actualProduct, searchTerm);

        // Add the product to the cart
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

        addTcpCoachingDayToCart();
        productListingPage.addProductToCart("TCP Public Complete");
        productListingPage.addProductToCart("TCP Public MT/AT");
        productListingPage.addProductToCart("TCP Public RPA/TCD");
        productListingPage.addProductToCart("TCP Self-Paced Training");
        productListingPage.addProductToCart("TCP Self-Paced Training additional month");

        String actualHeaderQty = basePage.getHeaderCartQty("(6)");
        Assert.assertEquals(actualHeaderQty, "(6)", "The number in the header is not 6");

        String actualFlyoutText = basePage.getFlyoutItemsCountMessage();

        Assert.assertTrue(actualFlyoutText.contains("6 item(s)"), "In the flyout menu does not write Quantity: 6!. It is: " + actualFlyoutText);


        // This will pause the test until 6 appears, or throw a timeout exception after 10 seconds
        String actualHeaderQty2 = basePage.getHeaderCartQty("(6)");
        Assert.assertEquals(actualHeaderQty2, "(6)", "Бројот во хедерот не е 6!");

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
    }


}
