package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigurationReader;
import utilities.Driver;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id="prependedInput")
    private WebElement userNameInput;
    @FindBy(id="prependedInput2")
    private WebElement passwordInput;
    @FindBy(id="_submit")
    private WebElement submitBtn;
    @FindBy(xpath = "//div[contains(@class,'alert')]")
    private WebElement errorMessage;

    public WebElement getUserNameInput(){
        return userNameInput;
    }
    public WebElement getPasswordInput(){
        return passwordInput;
    }
    public WebElement getSubmitBtn(){
        return submitBtn;
    }
    public String getErrorMessage(){
        return errorMessage.getText();
    }
    public void login(){
        getUserNameInput().sendKeys(ConfigurationReader.getProperty("username"));
        getPasswordInput().sendKeys(ConfigurationReader.getProperty("password"));
        getSubmitBtn().click();
    }
    public void login(String userName, String password){
        getUserNameInput().sendKeys(userName);
        getPasswordInput().sendKeys(password);
        getSubmitBtn().click();
    }
}
