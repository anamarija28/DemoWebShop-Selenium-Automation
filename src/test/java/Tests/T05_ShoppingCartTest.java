package Tests;

import Base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class T05_ShoppingCartTest extends BaseClass {

    public void setupCartWithProducts() {

        //Computers -> Desktops
        basePage.hoverComputersAndClickDesktops();
        productListingPage.selectProductByTitle("Build your own computer");


        configurableProductPage.selectHdd400Gb();
        configurableProductPage.clickAddToCartButton();

        // Computers -> Accessories
        basePage.hoverComputersAndClickAccessories();
        productListingPage.addProductToCart("TCP Instructor Led Training");


        // Apparel and Shoes
        basePage.clickApparelAndShoesTxtLink();
        productListingPage.addProductToCart("Blue Jeans");
        productListingPage.addProductToCart("Casual Golf Belt");

        basePage.clickHeaderShoppingCart();
        String actualTitle = shoppingCartPage.getPageTitle();
        Assert.assertEquals(actualTitle, "Shopping cart", "The page name is not Shopping cart!");
    }


    @Test
    public void verifyEmptyCart() {
        basePage.clickHeaderShoppingCart();

        String actualMessage = shoppingCartPage.getEmptyCartMessage().trim();
        Assert.assertEquals(actualMessage, "Your Shopping Cart is empty!", "The message for empty cart is wrong!");

        int productCount = shoppingCartPage.getNumberOfProductsInCart();
        Assert.assertEquals(productCount, 0, "The shopping cart is not empty!");

    }


    @Test
    public void verifyProductsInCart() {
        setupCartWithProducts();

        // Дефинирај КОИ продукти очекуваш да ги видиш
        List<String> expectedProducts = Arrays.asList(
                "Build your own computer",
                "TCP Instructor Led Training",
                "Blue Jeans",
                "Casual Golf Belt"
        );

        // Земи ги РЕАЛНИТЕ продукти
        List<String> actualProducts = shoppingCartPage.getAllProductNames();

        // 4. Верификација: Провери дали големината на листите е иста
        Assert.assertEquals(actualProducts.size(), expectedProducts.size(), "The number of products in the cart is wrong!");

        // 5. Верификација: Провери дали сите очекувани имиња се присутни во реалната листа
        for (String expectedName : expectedProducts) {
            Assert.assertTrue(actualProducts.contains(expectedName),
            "The product " + expectedName + "is not found in the cart!");
        }
    }

    @Test
    public void updateQuantityInCart() {
        setupCartWithProducts();

        shoppingCartPage.updateQuantityForProduct("Build your own computer", "2");

        String actualQuantity = shoppingCartPage.getProductQuantity("Build your own computer");
        Assert.assertEquals(actualQuantity, "2", "The quantity of the product is incorrect!");

    }

    @Test
    public void removeProductsFromCart() throws InterruptedException {
        setupCartWithProducts();

        shoppingCartPage.selectRemoveCheckboxForProduct("Blue Jeans");
        Thread.sleep(2000);
        shoppingCartPage.clickUpdateCartButton();
        Thread.sleep(2000);

        List<String> remainingProducts = shoppingCartPage.getAllProductNames();

        Assert.assertFalse(remainingProducts.contains("Blue Jeans"), "The product 'Blue Jeans' is not removed from the shopping cart!");

    }


    @Test
    public void verifyTotalPrice() {

        setupCartWithProducts();

        // Presmetuvame KOLKU treba da bidi (zbir od site redovi)
        double expectedTotal = shoppingCartPage.calculateSubtotalsSum();

        // Citame kolku sajtot VELI deka e ustvari (dolu desno)
        double actualTotal = shoppingCartPage.getCartTotalPrice();

        Assert.assertEquals(actualTotal, expectedTotal, "The total price in the shopping cart is not correct calculated!");

    }


    @Test
    public void verifyCartPersistLogginLogoutLogin() {

        String email = "testtuserr@mail.com";
        String password = "Lozinka123@";

        basePage.clickLoginLink();
        loginPage.login(email, password);

        setupCartWithProducts();

        List<String> expectedProducts = shoppingCartPage.getAllProductNames();

        basePage.clickLogOut();
        basePage.clickLoginLink();


        loginPage.login(email, password);
        basePage.clickHeaderShoppingCart();

        List<String> actualProducts = shoppingCartPage.getAllProductNames();
        Assert.assertEquals(actualProducts, expectedProducts, "The products are not displayed or they are not the same after re-logging!");
    }

}

