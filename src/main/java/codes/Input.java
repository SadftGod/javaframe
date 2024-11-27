package codes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static codes.DriverProvider.waiter;

public class Input  extends ElementWrapper{
    public Input(WebElement element){
        super(element);
    }


    public boolean is_visible(){
        System.out.println(element+" is " + element.isDisplayed());
        return element.isDisplayed();
    }

    public  boolean is_enable(){
        return  element.isEnabled();
    }
    public void fill(String filler){
        element.sendKeys(filler);
    }

    public void waitForInput(){
        waiter.until(ExpectedConditions.visibilityOf(element));
        System.out.println("Waiting for " + element);
    }
}