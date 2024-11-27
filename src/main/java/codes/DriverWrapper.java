package codes;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DriverWrapper {
    private String[] supportedBrowsers = {"chrome", "firefox"};
    private WebDriver driver1;
    private WebDriver driver2;

    public WebDriver getDriver1() {
        return driver1;
    }

    public WebDriver getDriver2() {
        return driver2;
    }

    public static boolean areAllBrowsersSupported(String[] browserNames, String[] supportedBrowsers) {
        Set<String> supportedSet = new HashSet<>(Arrays.asList(supportedBrowsers));

        return Arrays.stream(browserNames).allMatch(supportedSet::contains);
    }

    @Step
    public void initializeDriver(String[] browserNames) {
        if (!areAllBrowsersSupported(browserNames, supportedBrowsers)) {
            throw new IllegalArgumentException("Some browsers are not supported! Supported browsers are: "
                    + Arrays.toString(supportedBrowsers));
        }
        if (browserNames.length == 0) {
            throw new IllegalArgumentException("No browser names provided!");
        }
        if (browserNames.length == 1) {
            driver1 = DriverProvider.getDriver(browserNames[0]);
            System.out.println("Initialized driver1 for: " + browserNames[0]);
        } else if (browserNames.length == 2) {
            driver1 = DriverProvider.getDriver(browserNames[0]);
            driver2 = DriverProvider.getDriver(browserNames[1]);
            System.out.println("Initialized driver1 for: " + browserNames[0]);
            System.out.println("Initialized driver2 for: " + browserNames[1]);
        }
        else{
            throw new IllegalArgumentException("Can initialize just chrome and firefox now");
        }
    }

}
