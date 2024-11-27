package codes;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;
import java.util.HashMap;

public class DriverProvider {
    private static final ThreadLocal<Map<String, WebDriver>> driverPool = ThreadLocal.withInitial(HashMap::new);
    public static WebDriverWait waiter;

    public static WebDriver getDriver(String browserName) {
        Map<String, WebDriver> drivers = driverPool.get();

        if (!drivers.containsKey(browserName)) {
            WebDriver driver = switch (browserName.toLowerCase()) {
                case "chrome" -> {
                    WebDriverManager.chromedriver().setup();
                    yield new ChromeDriver();
                }
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    yield new FirefoxDriver();
                }
                default -> throw new IllegalArgumentException("Browser not supported: " + browserName);
            };

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            waiter = new WebDriverWait(driver, Duration.ofSeconds(3));
            drivers.put(browserName, driver);
        }

        return drivers.get(browserName);
    }

    public static void quitDriver(String browserName) {
        Map<String, WebDriver> drivers = driverPool.get();
        if (drivers.containsKey(browserName)) {
            drivers.get(browserName).quit();
            drivers.remove(browserName);
        }
    }

    public static void quitAllDrivers() {
        Map<String, WebDriver> drivers = driverPool.get();
        for (WebDriver driver : drivers.values()) {
            driver.quit();
        }
        drivers.clear();
    }
}
