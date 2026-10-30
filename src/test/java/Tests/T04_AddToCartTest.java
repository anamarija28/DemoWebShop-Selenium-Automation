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

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("q=" + searchTerm));

        boolean resultsCount = searchResultsPage.areProductsDisplayed();
        Assert.assertTrue(resultsCount, "The products for the searched keyword are not displayed!");

        String actualProduct = searchResultsPage.getSearchFieldValue();
        Assert.assertEquals(actualProduct, "jeans");

        searchResultsPage.clickAddToCartButton();

        String actualSuccessMessage = searchResultsPage.getSuccessMessage();
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
    public void addSameProductTwice()throws InterruptedException {

        basePage.hoverComputersAndClickAccessories();

        // Прво додавање
        productListingPage.addProductToCart("TCP Instructor Led Training");

        // Второ додавање (пораката ја враќа методот)
        String actualSuccessMessage = productListingPage.addProductToCart("TCP Instructor Led Training");
        Assert.assertEquals(actualSuccessMessage, "The product has been added to your shopping cart");

        String actualHeaderQty = basePage.getHeaderCartQty("(2)");
        Assert.assertEquals(actualHeaderQty, "(2)", "The number in the header is not 2!");

        String actualFlyoutText = basePage.getFlyoutProductQuantity();
        Assert.assertTrue(actualFlyoutText.contains("2"), "In the flyout menu does not write Quantity: 2!. It is: " + actualFlyoutText);
    }

    @Test
    public void addMultipleDifferentProducts() throws InterruptedException {

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


        // Ова ќе го стопира тестот додека не се појави (6), или ќе фрли Timeout по 10 секунди
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

        // Селектирање опции
        configurableProductPage.selectProcessor("2.5 GHz Intel Pentium Dual-Core E2200 [+15.00]");
        configurableProductPage.selectRamByValue("17");

        // Radio buttons
        configurableProductPage.selectHdd400Gb();
        configurableProductPage.selectWindows10();

        // Check options
        configurableProductPage.selectOffice();
        configurableProductPage.selectAcrobatReader();
        configurableProductPage.selectTotalCommander();

        configurableProductPage.clickAddToCartButton();

        String successMsg = configurableProductPage.getSuccessMessage();
        Assert.assertEquals(successMsg, "The product has been added to your shopping cart");

    }


}
