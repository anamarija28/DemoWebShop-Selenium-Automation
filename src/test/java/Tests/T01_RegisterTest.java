package Tests;

import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class T01_RegisterTest extends BaseClass {

//    public static String uniqueEmail = "testuser" + System.currentTimeMillis() + "@mailinator.com";

    @Test
    public void testValidRegistration() {
        String email = "testuser" + System.currentTimeMillis() + "@mailinator.com";

        basePage.clickRegisterLink();
        registerPage.register("Ime", "Prezime", email, "lozinka123@", "lozinka123@");

//        String actualSuccessMessage = registerResultPage.successRegistration();
//        Assert.assertEquals(actualSuccessMessage, "Your registration completed");
          Assert.assertEquals(registerResultPage.successRegistration(), "Your registration completed");

    }

    @Test
    public void testRegistrationWithExistingEmail(){
        String email = "testuser" + System.currentTimeMillis() + "@mailinator.com";

        // First registration
        basePage.clickRegisterLink();
        registerPage.register("Ime", "Prezime", email, "lozinka123@$", "lozinka123@$");
        registerResultPage.successRegistration();

        // Logging out
        basePage.clickLogOut();

        // Second registration with the same email
        basePage.clickRegisterLink();
        registerPage.register("Ime", "Prezime", email, "lozinka123@", "lozinka123@");


//      String actualExistingEmailMessage = registerPage.getRegistrationErrorMessage();
//      Assert.assertEquals(actualExistingEmailMessage, "The specified email already exists");
        Assert.assertTrue(registerPage.getRegistrationErrorMessage().contains("The specified email already exists"));

    }

    @Test
    public void testRegistrationWithMissingFields(){
        basePage.clickRegisterLink();
        registerPage.clickRegisterButton();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(registerPage.getFirstNameError(), "First name is required.");
        softAssert.assertEquals(registerPage.getLastNameError(), "Last name is required.");
        softAssert.assertEquals(registerPage.getEmailError(), "Email is required.");
        softAssert.assertEquals(registerPage.getPasswordError(), "Password is required.");
        softAssert.assertEquals(registerPage.getConfirmPasswordError(), "Password is required.");

        softAssert.assertAll();
    }

    @Test
    public void testRegistrationWithInvalidEmail(){
        basePage.clickRegisterLink();
        registerPage.register("Test", "TestUser", "testuser12@mailinator", "testuser12@", "testuser12@");

        String actualInvalidEmailMessage = registerPage.getEmailError();
        Assert.assertEquals(actualInvalidEmailMessage, "Wrong email");
    }

    @Test
    public void testRegistrationWithMismatchedPasswords(){
        basePage.clickRegisterLink();
        registerPage.register("Tuckie", "Hales", "tuckie@mailinator.com", "tuckie123!", "tuckie123!!");

        String actualMismatchPassMessage = registerPage.getConfirmPasswordError();
        Assert.assertEquals(actualMismatchPassMessage, "The password and confirmation password do not match.");
    }

    @Test
    public void testPasswordLength(){
        basePage.clickRegisterLink();
        registerPage.register("Karlis", "Laurant", "karlis@yahoo.com", "Pas12", "Pas12");

        Assert.assertEquals(registerPage.getPasswordLengthValidation(), "The password should have at least 6 characters.");

    }

}
