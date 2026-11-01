package Tests;

import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;


public class T02_LoginTest extends BaseClass {

    @Test
    public void testValidLogin(){

        String email = "testtuserr@mail.com";
        String password = "Lozinka123@";

        basePage.clickLoginLink();
        loginPage.login(email, password);

        Assert.assertFalse(loginPage.isLoginErrorDisplayed(), "Invalid logging in, an error message occured");

        System.out.println("Successful logging in! Now checking if the displayed email corresponds with: " + email);

        String actualEmail = basePage.getLoggedInUserEmail();

        System.out.println("Actual email found on Home Page: " + actualEmail);

        Assert.assertEquals(actualEmail, email, "Email does not match!");

    }

    @Test
    public void testInvalidEmailEntered(){

        String email = "anamarija@com";
        String password = "Anamarija123@";

        basePage.clickLoginLink();
        loginPage.login(email, password);

        Assert.assertEquals(loginPage.getInvalidEmailMessage(), "Please enter a valid email address.");
    }

    @Test
    public void testIncorrectPasswordEntered() {
        String email = "madison@mailinator.com";
        String password = "madison111";

        basePage.clickLoginLink();
        loginPage.login(email, password);

        Assert.assertEquals(loginPage.getIncorrectCredentialsMessage(), "The credentials provided are incorrect");

    }

    @Test
    public void testAccountNotFound() {
        String email = "madison111@mailinator.com";
        String password = "madison";

        basePage.clickLoginLink();
        loginPage.login(email, password);

        Assert.assertEquals(loginPage.getAccountNotFoundMessage(), "No customer account found");

    }

    @Test
    public void testRequiredFieldsEmpty() {
        basePage.clickLoginLink();
        loginPage.clickLoginButton();

        Assert.assertEquals(loginPage.getAccountNotFoundMessage(), "No customer account found");
    }

}
