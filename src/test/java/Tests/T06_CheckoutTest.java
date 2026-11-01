package Tests;

import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class T06_CheckoutTest extends BaseClass{

    public void proceedToCheckoutLoggedIn() {
        String email = "testtuserr@mail.com";
        String password = "Lozinka123@";

        basePage.clickLoginLink();
        loginPage.login(email, password);

        basePage.hoverComputersAndClickAccessories();
        productListingPage.addProductToCart("TCP Public Complete");
        basePage.clickHeaderShoppingCart();
        shoppingCartPage.acceptTermsAndCheckout();
    }

    public void proceedToCheckoutGuestUser() {

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

    @Test
    public void loggedInUserCheckout() {

        proceedToCheckoutLoggedIn();

        checkoutPage.clickBillingAddressContinueButton();
        checkoutPage.clickShippingAddressContinueButton();
        completeCheckout();

        String expectedMessage = "Your order has been successfully processed!";
        String actualMessage = checkoutPage.getOrderSuccessMessage();

        Assert.assertEquals(actualMessage, expectedMessage, "Нарачката не беше успешна!");
    }

    @Test
    public void guestUserCheckout() {

        proceedToCheckoutGuestUser();

        checkoutAsGuestPage.clickCheckoutAsGuest();
        checkoutPage.fillOutBillingAddress("Liza", "Toun", "lizatoun@mailinator.com","United States", "Chicago", "Avenue Test 123", "1000", "+15091236666");
        checkoutPage.clickShippingAddressContinueButton();
        completeCheckout();

        String expectedMessage = "Your order has been successfully processed!";
        String actualMessage = checkoutPage.getOrderSuccessMessage();

        Assert.assertEquals(actualMessage, expectedMessage, "Нарачката не беше успешна!");
    }

}
