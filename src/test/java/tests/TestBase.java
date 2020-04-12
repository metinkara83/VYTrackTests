package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CalendarEventsPage;
import pages.LoginPage;
import utilities.BrowserUtilities;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.io.IOException;

public abstract class TestBase {
    protected WebDriverWait wait;
    protected Actions actions;
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest logger;

    @BeforeTest
    @Parameters("reportName")
    public void setupTest(@Optional String reportName){
//        System.out.println("Report name: "+reportName);
        reportName = reportName == null ? "report.html" : reportName+".html";
        report = new ExtentReports();
        String reportPath = "";
        if(System.getProperty("os.name").toLowerCase().contains("win")){
            reportPath = System.getProperty("user.dir")+"\\test-output\\"+reportName;
        } else {
            reportPath = System.getProperty("user.dir")+"/test-output/"+reportName;
        }
        htmlReporter = new ExtentHtmlReporter(reportPath);
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("VYTrack Test Automation Results");
    }

    @AfterTest
    public void afterTest(){
        report.flush();
    }

    @BeforeMethod
    public void setup(){
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        wait = new WebDriverWait(Driver.getDriver(),30);
        actions = new Actions(Driver.getDriver());
        LoginPage loginPage = new LoginPage();
        loginPage.login();
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) throws IOException {
        if (testResult.getStatus()==ITestResult.FAILURE){
            String screenshotPath = BrowserUtilities.getScreenshot(testResult.getName());
            logger.fail(testResult.getName());
            logger.addScreenCaptureFromPath(screenshotPath, "Failed");
            logger.fail(testResult.getThrowable());
        }
        Driver.closeDriver();
    }
}
