package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterResultPage extends AbstractPage{

    public RegisterResultPage (WebDriver driver) {
        super(driver);
    }

    // lokatori
    private By successMessage = By.xpath("//div[@class='result']");


    // metodi i akcii
    public String successRegistration(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
}
