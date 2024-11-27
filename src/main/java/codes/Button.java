package codes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static codes.DriverProvider.waiter;

public class Button extends ElementWrapper{
    public Button(WebElement element){
        super(element);
    }

    public void click(){
        element.click();
        System.out.println("Click on " + element);

    }
    public boolean is_visible(){
        System.out.println(element+" is " + element.isDisplayed());
        return element.isDisplayed();
    }
    public  boolean is_enable(){
        return  element.isEnabled();
    }
    public void waitForButton(){
        waiter.until(ExpectedConditions.visibilityOf(element));
        System.out.println("Waiting for " + element);
    }
}
