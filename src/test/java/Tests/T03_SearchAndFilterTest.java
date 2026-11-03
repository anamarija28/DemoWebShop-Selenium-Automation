package Tests;
import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T03_SearchAndFilterTest extends BaseClass {

    private void navigateToApparelAndShoesAndValidate() {
        basePage.clickApparelAndShoesLink();

        Assert.assertTrue(driver.getCurrentUrl().contains("apparel-shoes"));
        Assert.assertEquals(productListingPage.getPageTitle(), "Apparel & Shoes");
    }

    @Test
    public void searchForValidProduct() {

        String searchTerm = "jeans";

        basePage.searchProduct(searchTerm);

        boolean isUrlCorrect = searchResultsPage.isUrlContaining("q=" + searchTerm);
        Assert.assertTrue(isUrlCorrect, "The URL does not contain the search term");

        boolean resultsCount = searchResultsPage.areProductsDisplayed();
        Assert.assertTrue(resultsCount, "The products for the searched keyword are not displayed!");

        String actualProduct = searchResultsPage.getSearchFieldValue();
        Assert.assertEquals(actualProduct, searchTerm);

        List<String> productNames = searchResultsPage.getProductNames();
        for (String name : productNames) {
            Assert.assertTrue(name.toLowerCase().contains(searchTerm),
                    "The product '" + name + "' does not match the search term '" + searchTerm + "'");
        }
    }

    @Test
    public void sortProductsByPrice() {

        navigateToApparelAndShoesAndValidate();

        productListingPage.selectSortOption("Price: Low to High");
        List<Double> actualPricesFromLow = productListingPage.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPricesFromLow);
        Collections.sort(expectedPrices);
        Assert.assertEquals(actualPricesFromLow, expectedPrices, "The prices are not correctly sorted from Low to High!");

        productListingPage.selectSortOption("Price: High to Low");
        List<Double> actualPricesFromHigh = productListingPage.getProductPrices();
        List<Double> expectedPricesFromHigh = new ArrayList<>(actualPricesFromHigh);
        Collections.sort(expectedPricesFromHigh, Collections.reverseOrder());

        Assert.assertEquals(actualPricesFromHigh, expectedPricesFromHigh, "The prices are not correctly sorted from High to Low!");
    }

    @Test
    public void sortProductsByName() {

        navigateToApparelAndShoesAndValidate();

        productListingPage.selectSortOption("Name: A to Z");
        List<String> actualNamesFromA = productListingPage.getProductNames();
        List<String> expectedNamesFromA = new ArrayList<>(actualNamesFromA);
        Collections.sort(expectedNamesFromA, String.CASE_INSENSITIVE_ORDER);
        Assert.assertEquals(actualNamesFromA, expectedNamesFromA, "The names are not sorted from A to Z");

        productListingPage.selectSortOption("Name: Z to A");
        List<String> actualNamesFromZ = productListingPage.getProductNames();
        List<String> expectedNamesFromZ = new ArrayList<>(actualNamesFromZ);
        Collections.sort(expectedNamesFromZ, String.CASE_INSENSITIVE_ORDER.reversed());
        Assert.assertEquals(actualNamesFromZ, expectedNamesFromZ, "The names are not sorted from Z to A");
    }


    @Test
    public void testDisplayPerPage() {

        navigateToApparelAndShoesAndValidate();

        productListingPage.selectDisplayPerPageOptions("4");
        Assert.assertEquals(productListingPage.getNumberOfProductsDisplayed(), 4, "Expected 4 products on the page");

        productListingPage.selectDisplayPerPageOptions("8");
        Assert.assertEquals(productListingPage.getNumberOfProductsDisplayed(), 8, "Expected 8 products on the page");

        productListingPage.selectDisplayPerPageOptions("12");
        Assert.assertTrue(productListingPage.getNumberOfProductsDisplayed() <= 12, "There are more than 12 products displayed");
    }

    @Test
    public void testViewAsProducts() {

        navigateToApparelAndShoesAndValidate();

        productListingPage.selectViewMode("List");
        Assert.assertTrue(productListingPage.isListViewDisplayed(), "The products are not displayed in a List view!");

        productListingPage.selectViewMode("Grid");
        Assert.assertTrue(productListingPage.isGridViewDisplayed(), "The products are not displayed in a Grid view!");
    }

    @Test
    public void testPaginationNumbersGrid() {
        navigateToApparelAndShoesAndValidate();

        productListingPage.selectDisplayPerPageOptions("4");

        Assert.assertEquals(productListingPage.getCurrentPageNumber(), "1", "Should start on page 1");

        productListingPage.clickPageNumber("2");

        String activePageAfterClick = productListingPage.getCurrentPageNumber();
        Assert.assertEquals(activePageAfterClick, "2", "After clicking '2', the active page number should be 2!");

        Assert.assertTrue(driver.getCurrentUrl().contains("pagenumber=2"), "The URL should contain pagenumber=2");

        productListingPage.clickPageNumber("3");
        Assert.assertEquals(productListingPage.getCurrentPageNumber(), "3");

        productListingPage.clickPageNumber("4");
        Assert.assertEquals(productListingPage.getCurrentPageNumber(), "4");
    }

    @Test
    public void testNavigationButtonsList() {
        navigateToApparelAndShoesAndValidate();

        productListingPage.selectDisplayPerPageOptions("4");
        productListingPage.selectViewMode("List");

        Assert.assertTrue(productListingPage.isListViewDisplayed(), "The products are not displayed in a List view!");

        productListingPage.goToLastPage();
        Assert.assertFalse(productListingPage.isNextPageButtonDisplayed(), "Next button should not be visible on the last page");

        productListingPage.goToFirstPage();
        Assert.assertFalse(productListingPage.isPreviousPageButtonDisplayed(), "Previous button should not be visible on the first page");
    }
}
