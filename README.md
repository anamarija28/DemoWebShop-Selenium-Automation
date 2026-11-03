# Demo Web Shop – Selenium Automation

This project contains automated UI tests for [Demo Web Shop](https://demowebshop.tricentis.com/), an online store used for practicing web application testing.

I built this project to practice Selenium WebDriver with Java and automate different user flows, from creating an account and browsing products to managing a shopping cart and completing an order.

The tests are organized by functionality, so each test class covers a specific part of the application. I used the Page Object Model (POM) to keep page elements and actions separate from the test logic.

## Tech Stack

- **Java 25** – Programming language
- **Selenium WebDriver 4.19.0** – Browser automation
- **TestNG 7.8.0** – Test organization and execution
- **Maven** – Dependency management
- **IntelliJ IDEA** – Development environment
- **Chrome** – Browser used for testing

## Project Structure

```text
src
└── test
    └── java
        ├── Base
        │   └── BaseClass.java
        │
        ├── Pages
        │   ├── AbstractPage.java
        │   ├── BasePage.java
        │   ├── CheckoutAsGuestPage.java
        │   ├── CheckoutPage.java
        │   ├── ConfigurableProductPage.java
        │   ├── LoginPage.java
        │   ├── ProductDetailsPage.java
        │   ├── ProductListingPage.java
        │   ├── RegisterPage.java
        │   ├── RegisterResultPage.java
        │   ├── SearchResultsPage.java
        │   ├── ShoppingCartPage.java
        │   └── WishlistPage.java
        │
        └── Tests
            ├── T01_RegisterTest.java
            ├── T02_LoginTest.java
            ├── T03_SearchAndFilterTest.java
            ├── T04_AddToCartTest.java
            ├── T05_ShoppingCartTest.java
            ├── T06_CheckoutTest.java
            └── T07_EndToEndTest.java
```

## Automated Test Scenarios

The project currently contains **34 automated tests**, organized into seven test classes.

### T01 – User Registration
- Register a new user with valid details.
- Attempt registration with an email address that already exists.
- Submit the registration form with empty required fields.
- Validate an incorrectly formatted email address.
- Check validation when passwords do not match.
- Verify the minimum password length requirement.

### T02 – User Login
- Log in with valid credentials.
- Attempt login with an invalid email format.
- Attempt login with an incorrect password.
- Attempt login with a non-existing account.
- Submit the login form with empty fields.
- Log out and verify that the user is logged out.

### T03 – Search and Filter
- Search for products using a keyword and verify the results.
- Sort products by price (Low to High and High to Low).
- Sort products by name (A to Z and Z to A).
- Change the number of products displayed per page (4, 8 and 12).
- Switch between Grid and List views.
- Navigate through product pages using pagination.
- Verify the Next and Previous navigation buttons.

### T04 – Add to Cart
- Add a product to the cart from search results.
- Add a product through category navigation.
- Add the same product multiple times.
- Add multiple different products and verify the item count.
- Configure a product by selecting its available options before adding it to the cart.

### T05 – Shopping Cart
- Verify the empty cart message and item count.
- Verify that the expected products are displayed in the cart.
- Update product quantities.
- Remove products and verify the remaining cart contents.
- Compare the cart total with the sum of product subtotals.
- Verify that cart contents remain available after logging out and logging back in.

### T06 – Checkout
- Complete successful checkout as a logged-in user.
- Complete successful checkout as a guest by providing billing details.
- Verify that checkout cannot proceed without accepting the Terms of Service.

### T07 – End-to-End Test
- Register a new user and verify successful registration.
- Add products to the Wishlist and Shopping Cart.
- Update product quantities and verify cart contents.
- Move Wishlist products to the Shopping Cart.
- Complete the checkout process as a logged-in user.
- Verify successful order placement and order number generation.

## Implementation Details

A few things about how the tests are structured:

- **Page Object Model (POM):** Page classes contain the elements and actions used to interact with the application, while test classes contain the test scenarios and assertions.
- **BaseClass:** Initializes ChromeDriver, opens the Demo Web Shop application, creates the Page Objects before each test and closes the browser after execution.
- **AbstractPage:** Provides a shared WebDriver instance and an explicit wait with a 10-second timeout.
- **TestNG:** Uses `@BeforeMethod` and `@AfterMethod` for test setup and cleanup.
- **Assertions:** Used to verify displayed messages, product details, quantities, sorting results, cart totals and order confirmations.

Chrome is launched in Incognito mode for each test execution.

## Running the Tests

The tests can be executed directly from IntelliJ IDEA:

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Make sure JDK 25 is configured.
4. Allow Maven to load the project dependencies.
5. Open the `Tests` package under `src/test/java`.
6. Right-click the `Tests` package and select **Run 'Tests'** to execute the test classes.

You can also run an individual test method or an entire test class separately.

## Test Execution Results

<details>
<summary>Latest full test run</summary>

| Result | Count |
|---|---:|
| Total tests | 34 |
| Passed | 34 |
| Failed | 0 |
| Skipped | 0 |

**Result:** All 34 tests passed in the latest full execution.

</details>

---

This is a personal learning project, and I plan to keep improving the test structure and adding more automated scenarios as I continue practicing Selenium and Java.
