package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import utilities.BrowserUtilities;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;

public class CalendarEventsPage extends PageBase {

    @FindBy(xpath = "//table/tbody/tr/td[2]")
    private List<WebElement> tableTitles;
    @FindBy(xpath = "//table/tbody/tr/td[2]/following-sibling::td[7]/div/div")
    private List<WebElement> tableThreeDots;
    @FindBy(xpath = "//div[@class='loader-mask']/following-sibling::ul//a")
    private List<WebElement> actionsMenuItems;
    @FindBy(xpath = "//div[@class='loader-mask']/following-sibling::ul")
    private WebElement actionsMenu;
    @FindBy(xpath = "//a[@title='Grid Settings']")
    private WebElement gridSettingsButton;
    @FindBy(xpath = "//div[@class='dropdown-menu']//tbody//td[1]")
    private List<WebElement> gridSettingsDropdownTableName;
    @FindBy(xpath = "//div[@class='dropdown-menu']//tbody//td[3]/input")
    private List<WebElement> gridSettingsDropdownTableCheckbox;
    @FindBy(xpath = "//thead[@class='grid-header']//span[1]")
    private WebElement pageTableHeaderTitle;
    @FindBy(xpath = "//a[@title='Create Calendar event']")
    private WebElement createCalendarEventButton;
    @FindBy(xpath = "//a[@class='btn-success btn dropdown-toggle']")
    private WebElement saveAndCloseExpandButton;
    @FindBy(xpath = "//div[@class='btn-group pull-right open']//li/button")
    private List<WebElement> saveCloseExpandedButtons;
    @FindBy(xpath = "//a[@class='btn back icons-holder-text ']")
    private WebElement cancelButton;
    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement calendarEventsPageSubtitle;
    @FindBy(xpath = "//input[starts-with(@id,'time_selector_oro_calendar_event_form_start')]")
    private WebElement startTimeInput;
    @FindBy(xpath = "//input[starts-with(@id,'time_selector_oro_calendar_event_form_end')]")
    private WebElement endTimeInput;
    @FindBy(xpath = "//ul[@class='ui-timepicker-list']/li")
    private List<WebElement> timePicker;
    @FindBy(xpath = "//input[starts-with(@id,'oro_calendar_event_form_allDay-uid')]")
    private WebElement allDayEventCheckbox;
    @FindBy(xpath = "//input[starts-with(@id,'date_selector_oro_calendar_event_form_start')]")
    private WebElement startDateInput;
    @FindBy(xpath = "//input[starts-with(@id,'date_selector_oro_calendar_event_form_end')]")
    private WebElement endDateInput;
    @FindBy(xpath = "//input[starts-with(@id,'recurrence-repeat')]")
    private WebElement repeatCheckbox;
    @FindBy(xpath = "//select[starts-with(@id,'recurrence-repeats')]")
    private WebElement selectDropdownElements;
    @FindBy(xpath = "//span[contains(text(),'day(s)')]/preceding-sibling::input[3]")
    private WebElement repeatEveryRadioBtn;
    @FindBy(xpath = "//span[contains(text(),'Never')]/preceding-sibling::input")
    private WebElement neverEndsRadio;
    @FindBy(xpath = "//span[contains(text(),'After')]/preceding-sibling::input")
    private WebElement afterEndsRadio;
    @FindBy(xpath = "//span[contains(text(),'By')]/preceding-sibling::input")
    private WebElement byRadio;
    @FindBy(xpath = "//span[contains(text(),'After')]/following-sibling::input")
    private WebElement occurrencesInputBox;
    @FindBy(xpath = "//span[text()='By']/../following-sibling::span//input[2]")
    private WebElement dateInputBox;
    @FindBy(xpath = "//label[contains(text(),'Summary:')]/../following-sibling::div//span")
    private WebElement summaryMessage;
    @FindBy(xpath = "//label[contains(text(),'Summary:')]/../following-sibling::div//span[2]")
    private WebElement summaryMessage2;
    @FindBy(xpath = "//label[@class='multi-checkbox-control__item']/input")
    private List<WebElement> dayInputBoxes;
    @FindBy(xpath = "//label[@class='multi-checkbox-control__item']/span")
    private List<WebElement> dayInputBoxesLabels;

    public List<WebElement> getDayInputBoxes(){
        return dayInputBoxes;
    }

    public List<WebElement> getDayInputBoxesLabels(){
        return dayInputBoxesLabels;
    }

    public List<String> selectedRepeatOnDays(){
        List<String> selectedDays = new ArrayList<>();
        dayInputBoxes.get(1).click();
        dayInputBoxes.get(5).click();
        for (int i = 0 ; i < dayInputBoxes.size() ; i++){
            if(dayInputBoxes.get(i).isSelected()){
                selectedDays.add(dayInputBoxesLabels.get(i).getText());
            }
        }
        return selectedDays;
    }

    public WebElement getDateInputBox(){
        return dateInputBox;
    }

    public WebElement getByRadio(){
        return byRadio;
    }

    public String getSummaryMessageText2(){
        return summaryMessage2.getText();
    }

    public WebElement getOccurrencesInputBox(){
        return occurrencesInputBox;
    }

    public String getSummaryMessageText(){
        return summaryMessage.getText();
    }

    public WebElement getAfterEndsRadio(){
        return afterEndsRadio;
    }

    public WebElement getNeverEndsRadio(){
        return neverEndsRadio;
    }

    public WebElement getThreeDotElement(String specifiedText){
        int j = 0;
        for(int i = 0 ; i < tableTitles.size() ; i++){
            if(tableTitles.get(i).getText().equals(specifiedText)){
                j = i;
            }
        }
        return tableThreeDots.get(j);
    }

    public List<String> getActionsMenuItemsText(){
        List<String> actionsMenuItemsText = new ArrayList<>();
        for (WebElement eachMenu : actionsMenuItems) {
            actionsMenuItemsText.add(eachMenu.getAttribute("title"));
        }
        return actionsMenuItemsText;
    }

    public WebElement getActionsMenu(){
        return actionsMenu;
    }

    public WebElement getGridSettingsButton(){
        return gridSettingsButton;
    }

    public void deselectGridSettingsDropdownTableCheckbox(){
        for(int i = 0 ; i < gridSettingsDropdownTableName.size() ; i++){
            if (!gridSettingsDropdownTableName.get(i).getText().equals("Title")){
                gridSettingsDropdownTableCheckbox.get(i).click();
                BrowserUtilities.wait(1);
            }
        }
    }

    public String getPageTableHeaderTitleText(){
        return pageTableHeaderTitle.getText();
    }

    public void clickOnCreateCalendarEventButton(){
        createCalendarEventButton.click();
    }

    public WebElement getSaveAndCloseExpandButton(){
        wait.until(ExpectedConditions.elementToBeClickable(saveAndCloseExpandButton));
        return saveAndCloseExpandButton;
    }

    public List<String> getSaveCloseExpandedButtons(){
        List<String> saveCloseExpandedTexts = new ArrayList<>();
        for (int i = 0 ; i < saveCloseExpandedButtons.size() ; i++){
            saveCloseExpandedTexts.add(saveCloseExpandedButtons.get(i).getText().trim());
        }
        return saveCloseExpandedTexts;
    }

    public void clickOnCancelButton(){
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancelButton.click();
    }

    public WebElement getCalendarEventsPageSubtitle(){
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//h1[@class='oro-subtitle']"),"Create Calendar Event"));
        return calendarEventsPageSubtitle;
    }

    public Integer getHourDifference(){
        wait.until(ExpectedConditions.visibilityOf(startTimeInput));
        String startTime = startTimeInput.getAttribute("value");
        Integer startHourTime = Integer.parseInt(startTime.substring(0, startTime.indexOf(":")));
        String endTime = endTimeInput.getAttribute("value");
        Integer endHourTime = Integer.parseInt(endTime.substring(0, endTime.indexOf(":")));
        if (startHourTime==12){
            startHourTime = 0;
        }
        return (endHourTime-startHourTime);
    }

    public WebElement getStartHour(){
        wait.until(ExpectedConditions.visibilityOf(startTimeInput));
        return startTimeInput;
    }

    public WebElement getStartHourWithoutWaiting(){
        return startTimeInput;
    }

    public WebElement getEndHour(){
        wait.until(ExpectedConditions.visibilityOf(endTimeInput));
        return endTimeInput;
    }

    public WebElement getEndHourWithoutWaiting(){
        return endTimeInput;
    }

    public void select9AMStartHour(){
        wait.until(ExpectedConditions.visibilityOf(startTimeInput));
        startTimeInput.click();
        for (int i = 0 ; i < timePicker.size() ; i++){
            if(timePicker.get(i).getText().equals("9:00 AM")){
                timePicker.get(i).click();
                break;
            }
        }
    }

    public void selectAllDayEventCheckbox(){
        wait.until(ExpectedConditions.elementToBeClickable(allDayEventCheckbox));
        allDayEventCheckbox.click();
        BrowserUtilities.wait(3);
    }

    public WebElement getAllDayEventCheckbox(){
        return allDayEventCheckbox;
    }

    public WebElement getStartDateInput(){
        return startDateInput;
    }

    public WebElement getEndDateInput(){
        return endDateInput;
    }

    public void selectRepeatCheckbox(){
        wait.until(ExpectedConditions.elementToBeClickable(repeatCheckbox));
        repeatCheckbox.click();
        BrowserUtilities.wait(3);
    }

    public WebElement getRepeatCheckbox(){
        return repeatCheckbox;
    }

    public void selectWeeklyRepeats(){
        Select repeats = new Select(selectDropdownElements);
        repeats.selectByVisibleText("Weekly");
    }

    public String getSelectedDropdownElement(){
        Select repeats = new Select(selectDropdownElements);
        return repeats.getFirstSelectedOption().getText();
    }

    public List<String> getSelectDropdownElementList(){
        Select repeats = new Select(selectDropdownElements);
        List<WebElement> dropdownElements = repeats.getOptions();
        List<String> dropdownElementsText = new ArrayList<>();
        for (int i= 0 ; i < dropdownElements.size() ; i++){
            dropdownElementsText.add(dropdownElements.get(i).getText());
        }
        return dropdownElementsText;
    }

    public WebElement getRepeatEveryRadioBtn(){
        return repeatEveryRadioBtn;
    }

}
