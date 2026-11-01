package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterResultPage extends AbstractPage{

    public RegisterResultPage (WebDriver driver) {
        super(driver);
    }

    private By successMessage = By.xpath("//div[@class='result']");


    public String successRegistration(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
}
