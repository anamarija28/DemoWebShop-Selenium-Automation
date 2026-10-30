package Tests;

import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class T07_EndToEndTest extends BaseClass {

    public static String uniqueEmail = "testuser" + System.currentTimeMillis() + "@mailinator.com";


    @Test
    public void completelyEndToEnd() throws InterruptedException{

        // Register a new user
        basePage.clickRegisterLink();
        registerPage.register("Ferdinanda", "Upshall", uniqueEmail, "Pa77word1@", "Pa77word1@");

        String actualSuccessMessage = registerResultPage.successRegistration();
        Assert.assertEquals(actualSuccessMessage, "Your registration completed");

        // Add a product to Wishlist and update quantity
        basePage.clickApparelAndShoesTxtLink();
        productListingPage.clickPageNumber("2");
        productListingPage.selectProductByTitle("Men's Wrinkle Free Long Sleeve");
        productDetailsPage.setQuantity("2");
        productDetailsPage.clickAddToWishlist();

        basePage.clickApparelAndShoesTxtLink();
        productListingPage.selectProductByTitle("Blue and green Sneaker");
        productDetailsPage.clickAddToWishlist();


        // Add a product to the Cart
        basePage.clickApparelAndShoesTxtLink();
        productListingPage.selectProductByTitle("Blue Jeans");
        productDetailsPage.addToCart();

        // Navigating to Wishlist and adding the product to the Cart
        basePage.clickHeaderWishList();
        wishlistPage.selectAllAddToCartCheckboxes();
        wishlistPage.clickAddToCartButton();


        // Navigating to the Cart Page and updating the Cart
        shoppingCartPage.updateQuantityForProduct("Blue and green Sneaker", "2");
        shoppingCartPage.clickUpdateCartButton();
        shoppingCartPage.acceptTermsAndCheckout();


        // Checkout
        checkoutPage.fillOutBillingAddressForLoggedInUser("United States", "Chicago", "Bolingbrook 12 Ave", "12355", "+1 5056487333");
        checkoutPage.clickShippingAddressContinueButton();
        checkoutPage.selectShippingMethod();
        checkoutPage.clickContinueShippingMethod();
        checkoutPage.selectPaymentMethod();
        checkoutPage.clickPaymentMethodContinueButton();
        checkoutPage.fillOutPaymentInformation("Master card", "Ferdinanda Upshall", "5433300000000141", "11", "2027", "1236");
        checkoutPage.clickPaymentInfoContinueButton();
        checkoutPage.clickConfirmOrderButton();

        String expectedMessage = "Your order has been successfully processed!";
        String actualMessage = checkoutPage.getOrderSuccessMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Нарачката не беше успешна!");


        // Tuka go zemame dinamickiot tekst (pr. "Order number: 2300259")
        String orderText = checkoutPage.getOrderNumberText();

        // Proveruvame dali tekstot voopsto go sodrzi glavniot zbor
        Assert.assertTrue(orderText.contains("Order number:"), "Текстот за број на нарачка не е пронајден!");

        // Go cistime tekstot za da ni ostani samo brojkata i proveruvame deka ne e prazna
        String justTheNumber = orderText.replace("Order number:", "").trim();
        Assert.assertFalse(justTheNumber.isEmpty(), "Бројот на нарачката е празен!");

        // Mozis da si isprintas vo konzola za da vidis deka narackata uspesno se kreirala
        System.out.println("Успешно креирана нарачка со динамички број: " + justTheNumber);
    }

}
