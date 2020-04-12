package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CalendarEventsPage;
import utilities.BrowserUtilities;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarEventsTest extends TestBase{
    private Actions a = new Actions(Driver.getDriver());

    @Test(priority = 0)
    public void test1(){
        logger = report.createTest("Verify that \"view\",\"edit\" and \"delete\" options are available.");
        BrowserUtilities.waitForPageToLoad(30L);
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        a.moveToElement(calendarEventsPage.getThreeDotElement("Testers Meeting")).pause(2000).build().perform();
        List<String> expectedMenu = Arrays.asList("View","Edit","Delete");
        Assert.assertEquals(calendarEventsPage.getActionsMenuItemsText(),expectedMenu);
        Assert.assertTrue(calendarEventsPage.getActionsMenu().isDisplayed());
    }

    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        logger = report.createTest("Verify that \"title\" column still displayed.");
        BrowserUtilities.waitForPageToLoad(30L);
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.getGridSettingsButton().click();
        calendarEventsPage.deselectGridSettingsDropdownTableCheckbox();
        Assert.assertEquals(calendarEventsPage.getPageTableHeaderTitleText(),"TITLE");
    }

    @Test(dependsOnMethods = {"test2"})
    public void test3(){
        logger = report.createTest("Verify that \"Save And Close\", \"Save And New\", \"Save\" options are available.");
        BrowserUtilities.waitForPageToLoad(30L);
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickOnCreateCalendarEventButton();
        calendarEventsPage.getSaveAndCloseExpandButton().click();
        List<String> expectedResults = Arrays.asList("Save And Close","Save And New","Save");
        Assert.assertEquals(calendarEventsPage.getSaveCloseExpandedButtons(),expectedResults);
    }

    @Test(dependsOnMethods = {"test3"})
    public void test4(){
        logger = report.createTest("Verify that \"All Calendar Events\" page subtitle is displayed.");
        BrowserUtilities.waitForPageToLoad(30L);
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickOnCreateCalendarEventButton();
        calendarEventsPage.clickOnCancelButton();
        String expected = "All Calendar Events";
        Assert.assertEquals(calendarEventsPage.getCalendarEventsPageSubtitle().getText(),expected);
    }

    @Test(dependsOnMethods = {"test4"})
    public void test5(){
        logger = report.createTest("Verify that difference between end and start time is exactly 1 hour.");
        BrowserUtilities.waitForPageToLoad(30L);
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickOnCreateCalendarEventButton();
        Assert.assertTrue(calendarEventsPage.getHourDifference()==1);
    }

    @Test(dependsOnMethods = {"test5"})
    public void test6(){
        logger = report.createTest("Verify that end time equals to 10.");
        BrowserUtilities.waitForPageToLoad(30L);
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickOnCreateCalendarEventButton();
        calendarEventsPage.select9AMStartHour();
        Assert.assertEquals(calendarEventsPage.getEndHour().getAttribute("value"),"10:00 AM");
    }

    @Test(dependsOnMethods = {"test6"})
    public void test7(){
        logger = report.createTest("Verify that \"All Day Event\" checkbox is selected.");
        BrowserUtilities.waitForPageToLoad(30L);
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickOnCreateCalendarEventButton();
        calendarEventsPage.selectAllDayEventCheckbox();
        Assert.assertTrue(calendarEventsPage.getAllDayEventCheckbox().isSelected());
        Assert.assertTrue(!calendarEventsPage.getStartHourWithoutWaiting().isDisplayed());
        Assert.assertTrue(!calendarEventsPage.getEndHourWithoutWaiting().isDisplayed());
        Assert.assertTrue(calendarEventsPage.getStartDateInput().isDisplayed());
        Assert.assertTrue(calendarEventsPage.getEndDateInput().isDisplayed());
    }

    @Test(dependsOnMethods = {"test7"})
    public void test8(){
        logger = report.createTest("Verify that \"Repeat\" checkbox is selected.");
        BrowserUtilities.waitForPageToLoad(30L);
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickOnCreateCalendarEventButton();
        calendarEventsPage.selectRepeatCheckbox();
        Assert.assertTrue(calendarEventsPage.getRepeatCheckbox().isSelected());
        Assert.assertEquals(calendarEventsPage.getSelectedDropdownElement(),"Daily");
        List<String> expected = Arrays.asList("Daily","Weekly","Monthly","Yearly");
        Assert.assertEquals(calendarEventsPage.getSelectDropdownElementList(),expected);
    }

    @Test(dependsOnMethods = {"test8"})
    public void test9(){
        logger = report.createTest("Verify that \"Repeat\" checkbox and related radioboxes are selected.");
        BrowserUtilities.waitForPageToLoad(30L);
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickOnCreateCalendarEventButton();
        calendarEventsPage.selectRepeatCheckbox();
        Assert.assertTrue(calendarEventsPage.getRepeatCheckbox().isSelected());
        Assert.assertTrue(calendarEventsPage.getRepeatEveryRadioBtn().isSelected());
        Assert.assertTrue(calendarEventsPage.getNeverEndsRadio().isSelected());
        Assert.assertEquals(calendarEventsPage.getSummaryMessageText(),"Daily every 1 day");
    }

    @Test(dependsOnMethods = {"test9"})
    public void test10(){
        logger = report.createTest("Verify that \"Repeat\" checkbox and after radio box are selected.");
        BrowserUtilities.waitForPageToLoad(30L);
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickOnCreateCalendarEventButton();
        calendarEventsPage.selectRepeatCheckbox();
        Assert.assertTrue(calendarEventsPage.getRepeatCheckbox().isSelected());
        Assert.assertTrue(calendarEventsPage.getRepeatEveryRadioBtn().isSelected());
        calendarEventsPage.getAfterEndsRadio().click();
        Assert.assertTrue(calendarEventsPage.getAfterEndsRadio().isSelected());
        calendarEventsPage.getOccurrencesInputBox().sendKeys("10",Keys.TAB);
        String expected = "Daily every 1 day, end after 10 occurrences";
        String actual = calendarEventsPage.getSummaryMessageText()+calendarEventsPage.getSummaryMessageText2();
        Assert.assertEquals(actual,expected);
    }

    @Test(dependsOnMethods = {"test10"})
    public void test11(){
        logger = report.createTest("Verify that \"Repeat\" checkbox and ends radio box are selected.");
        BrowserUtilities.waitForPageToLoad(30L);
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickOnCreateCalendarEventButton();
        calendarEventsPage.selectRepeatCheckbox();
        Assert.assertTrue(calendarEventsPage.getRepeatCheckbox().isSelected());
        Assert.assertTrue(calendarEventsPage.getRepeatEveryRadioBtn().isSelected());
        calendarEventsPage.getByRadio().click();
        Assert.assertTrue(calendarEventsPage.getByRadio().isSelected());
        calendarEventsPage.getDateInputBox().sendKeys("Nov 18, 2021",Keys.TAB, Keys.TAB);
        String expected = "Daily every 1 day, end by Nov 18, 2021";
        String actual = calendarEventsPage.getSummaryMessageText()+calendarEventsPage.getSummaryMessageText2();
        Assert.assertEquals(actual,expected);
    }

    @Test(dependsOnMethods = {"test11"})
    public void test12(){
        logger = report.createTest("Verify that \"Repeat Weekly\" is selected.");
        BrowserUtilities.waitForPageToLoad(30L);
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickOnCreateCalendarEventButton();
        calendarEventsPage.selectRepeatCheckbox();
        Assert.assertTrue(calendarEventsPage.getRepeatCheckbox().isSelected());
        calendarEventsPage.selectWeeklyRepeats();
        List<String> expectedSelection = Arrays.asList("M","F");
        Assert.assertEquals(calendarEventsPage.selectedRepeatOnDays(),expectedSelection);
        String expected = "Weekly every 1 week on Monday, Friday";
        String actual = calendarEventsPage.getSummaryMessageText();
        Assert.assertEquals(actual,expected);
    }
}
