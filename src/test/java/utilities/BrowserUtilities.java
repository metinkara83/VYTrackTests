package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrowserUtilities {
    private static WebDriverWait wait;

    public static void wait(int seconds){
        try{
            Thread.sleep(1000*seconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static List<String> getTextFromWebElements(List<WebElement> elements){
        List<String> textValues = new ArrayList<>();
        for (WebElement element : elements) {
            if(!element.getText().isEmpty()){
                textValues.add(element.getText());
            }
        }
        return textValues;
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void scrollTo(WebElement element){
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static String getScreenshot(String name){
        name = new Date().toString().replace(" ","_").replace(":","-") + "_" + name;
        String path = "";
        if(System.getProperty("os.name").toLowerCase().contains("mac")){
            path = System.getProperty("user.dir")+"test-output/screenshots/" + name + ".png";
        } else {
            path = System.getProperty("user.dir")+"\\test-output\\screenshots\\" + name + ".png";
        }
        System.out.println("Screenshot is here: "+path);
        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File(path);
        try{
            FileUtils.copyFile(source,destination);
        }catch (IOException e){
            e.printStackTrace();
        }
        return path;
    }

    public static void waitUntilElementDisplayed(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
