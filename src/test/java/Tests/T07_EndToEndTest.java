package Tests;
import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class T07_EndToEndTest extends BaseClass {

    @Test
    public void completelyEndToEnd() {

        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@mailinator.com";

        // Register a new user
        basePage.clickRegisterLink();
        registerPage.register("Ferdinanda", "Upshall", uniqueEmail, "Pa77word1@", "Pa77word1@");

        String actualSuccessMessage = registerResultPage.successRegistration();
        Assert.assertEquals(actualSuccessMessage, "Your registration completed");

        Assert.assertEquals(basePage.getLoggedInUserEmail(), uniqueEmail, "The registered user is not logged in!");

        // Add a product to Wishlist and update quantity
        basePage.clickApparelAndShoesLink();
        productListingPage.clickPageNumber("2");
        productListingPage.selectProductByTitle("Men's Wrinkle Free Long Sleeve");
        productDetailsPage.setQuantity("2");
        productDetailsPage.clickAddToWishlistButton();
        basePage.clickApparelAndShoesLink();
        productListingPage.selectProductByTitle("Blue and green Sneaker");
        productDetailsPage.clickAddToWishlistButton();

        // Add a product to the Cart
        basePage.clickApparelAndShoesLink();
        productListingPage.selectProductByTitle("Blue Jeans");
        productDetailsPage.clickAddToCartButton();

        // Navigating to Wishlist and adding the product to the Cart
        basePage.clickHeaderWishList();
//        System.out.println("Current URL: " + driver.getCurrentUrl());
//        System.out.println("Page title: " + driver.getTitle());

        List<String> wishlistProducts = wishlistPage.getProductNames();
        Assert.assertTrue(wishlistProducts.contains("Men's Wrinkle Free Long Sleeve"), "Wrinkle Free Long Sleeve is not in the wishlist!");
        Assert.assertTrue(wishlistProducts.contains("Blue and green Sneaker"), "Sneaker is not in the wishlist!");

        wishlistPage.selectAllAddToCartCheckboxes();
        wishlistPage.clickAddToCartButton();

        // Navigating to the Cart Page and updating the Cart
        List<String> productsInCart = shoppingCartPage.getAllProductNames();
        Assert.assertTrue(productsInCart.contains("Blue Jeans"), "Blue Jeans is not in the cart!");
        Assert.assertTrue(productsInCart.contains("Men's Wrinkle Free Long Sleeve"), "Wrinkle Free Long Sleeve is not in the cart!");

        shoppingCartPage.updateQuantityForProduct("Blue and green Sneaker", "2");
        Assert.assertEquals(shoppingCartPage.getProductQuantity("Blue and green Sneaker"), "2", "The sneaker quantity is not 2!");

        shoppingCartPage.acceptTermsAndCheckout();

        // Checkout
        checkoutPage.fillBillingAddressForLoggedInUser("United States", "Chicago", "Bolingbrook 12 Ave", "12355", "+1 5056487333");
        checkoutPage.clickShippingAddressContinueButton();
        checkoutPage.selectShippingMethod();
        checkoutPage.clickShippingMethodContinueButton();
        checkoutPage.selectPaymentMethod();
        checkoutPage.clickPaymentMethodContinueButton();
        checkoutPage.fillPaymentInformation("Master card", "Ferdinanda Upshall", "5433300000000141", "11", "2027", "1236");
        checkoutPage.clickPaymentInfoContinueButton();
        checkoutPage.clickConfirmOrderButton();

        String expectedMessage = "Your order has been successfully processed!";
        String actualMessage = checkoutPage.getOrderSuccessMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "The order was not successful!");

        String orderText = checkoutPage.getOrderNumberText();

        Assert.assertTrue(orderText.contains("Order number:"), "The order number text is not found!");

        // We clean the text to keep only the number and check that it is not empty.
        String justTheNumber = orderText.replace("Order number:", "").trim();
        Assert.assertFalse(justTheNumber.isEmpty(), "The order number is empty!");
    }
}
