package com.cydeo.utilities;

import org.junit.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrowserUtils {
    /*
    This method will accept int (in Seconds) and execute Thread.sleep for given duration
     */
    public static void sleep(int second){
        second*=1000;
        try{
            Thread.sleep(second);
        }catch (InterruptedException e){

        }
    }


    /*
    This method accepts 3 Arguments.
    Arg1: webdriver
    Arg2: expectedInUrl: for verıfy if the url contains given String.
        - If condition matches, will break loop
    Arg3: expectedInTitle to be compared against actualTitle
     */
    public static void switchWindowAndVerify( String expectedInUrl, String expectedTitle){
        Set<String> allWindowsHandles=Driver.getDriver().getWindowHandles();
        for (String each : allWindowsHandles) {
            Driver.getDriver().switchTo().window(each);
            System.out.println("Current URL: "+Driver.getDriver().getCurrentUrl());
            if (Driver.getDriver().getCurrentUrl().contains(expectedInUrl)){
                break;
            }

        }
        //5. Assert: Title contains “expectedTitle”
        String actualTitle=Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle));

    }


    /*
    This method accepts a String "expectedTitle" and Asserts if it is true
     */
    public static void verifyTitle(String expectedTitle){
        Assert.assertEquals(Driver.getDriver().getTitle(),expectedTitle);
    }

    /**
     *This method will verify if the current URL contains expected Value.
     * @param expectedInURL
     */
    public static void verifyURLContains(String expectedInURL){
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(expectedInURL));
    }

    /**
     * This method will accept a dropdown as a WebElement
     * and return all the options' text in a List of String
     * @param dropdownElement
     * @return List<String> actualOptionsAsString
     */
    public static List<String> dropdownOptionsAsString(WebElement dropdownElement){
        Select select =new Select(dropdownElement);
        // List of all ACTUAL month <options> as a string
        List<WebElement> actualoptionsAsWebElement= select.getOptions();

        // List of all ACTUAL month <options> as a string
        List<String> actualOptionsAsString=new ArrayList<>();
        for (WebElement each : actualoptionsAsWebElement){
            actualOptionsAsString.add(each.getText());
        }
        return actualOptionsAsString;
    }

    public static void clickRadioButtonWit(List<WebElement> radioButtons,String attributeValue){
        for (WebElement each : radioButtons) {
            if (each.getAttribute("value").equals(attributeValue)){
                each.click();
            }
        }
    }
}
