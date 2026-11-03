package Tests;
import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class T06_CheckoutTest extends BaseClass{

    private void proceedToCheckoutLoggedIn() {
        String email = "testtuserr@mail.com";
        String password = "Lozinka123@";

        basePage.clickLoginLink();
        loginPage.login(email, password);

        basePage.hoverComputersAndClickAccessories();
        productListingPage.addProductToCart("TCP Public Complete");
        basePage.clickHeaderShoppingCart();
        shoppingCartPage.acceptTermsAndCheckout();
    }

    private void proceedToCheckoutGuestUser() {

        basePage.hoverComputersAndClickAccessories();
        productListingPage.addProductToCart("TCP Public Complete");
        basePage.clickHeaderShoppingCart();
        shoppingCartPage.acceptTermsAndCheckout();
    }

    private void completeCheckout() {
        checkoutPage.selectShippingMethod();
        checkoutPage.clickShippingMethodContinueButton();
        checkoutPage.clickPaymentMethodContinueButton();
        checkoutPage.clickPaymentInfoContinueButton();
        checkoutPage.clickConfirmOrderButton();
    }

    private void verifyOrderPlaced() {
        String actualMessage = checkoutPage.getOrderSuccessMessage();
        Assert.assertEquals(actualMessage, "Your order has been successfully processed!", "The order was not successful!");

        String orderText = checkoutPage.getOrderNumberText();
        Assert.assertTrue(orderText.contains("Order number:"), "The order number text is not found!");

        String orderNumber = orderText.replace("Order number:", "").trim();
        Assert.assertFalse(orderNumber.isEmpty(), "The order number is empty!");
    }



    @Test
    public void loggedInUserCheckout() {
        proceedToCheckoutLoggedIn();

        checkoutPage.clickBillingAddressContinueButton();
        checkoutPage.clickShippingAddressContinueButton();
        completeCheckout();

        verifyOrderPlaced();
    }

    @Test
    public void guestUserCheckout() {
        proceedToCheckoutGuestUser();

        checkoutAsGuestPage.clickCheckoutAsGuest();
        checkoutPage.fillOutBillingAddress("Liza", "Toun", "lizatoun@mailinator.com","United States", "Chicago", "Avenue Test 123", "1000", "+15091236666");
        checkoutPage.clickShippingAddressContinueButton();
        completeCheckout();

        verifyOrderPlaced();
    }

    @Test
    public void checkoutWithoutAcceptingTerms() {
        basePage.hoverComputersAndClickAccessories();
        productListingPage.addProductToCart("TCP Public Complete");
        basePage.clickHeaderShoppingCart();

        shoppingCartPage.clickCheckoutWithoutAcceptingTerms();

        String actualMessage = shoppingCartPage.getTermsWarningMessage();
        Assert.assertTrue(actualMessage.contains("Please accept the terms of service"), "The terms of service warning is not displayed!");
    }

}
