package Tests;

import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class T07_EndToEndTest extends BaseClass {

    @Test
    public void completelyEndToEnd() {

        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@mailinator.com";

        // Register a new user
        basePage.clickRegisterLink();
        registerPage.register("Ferdinanda", "Upshall", uniqueEmail, "Pa77word1@", "Pa77word1@");

        String actualSuccessMessage = registerResultPage.successRegistration();
        Assert.assertEquals(actualSuccessMessage, "Your registration completed");

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
        wishlistPage.selectAllAddToCartCheckboxes();
        wishlistPage.clickAddToCartButton();

        // Navigating to the Cart Page and updating the Cart
        shoppingCartPage.updateQuantityForProduct("Blue and green Sneaker", "2");
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
        Assert.assertEquals(actualMessage, expectedMessage, "Нарачката не беше успешна!");


        String orderText = checkoutPage.getOrderNumberText();

        Assert.assertTrue(orderText.contains("Order number:"), "Текстот за број на нарачка не е пронајден!");

        // We clean the text to keep only the number and check that it is not empty.
        String justTheNumber = orderText.replace("Order number:", "").trim();
        Assert.assertFalse(justTheNumber.isEmpty(), "Бројот на нарачката е празен!");
    }

}
