package codes;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static codes.DriverProvider.getDriver;

public class ProductLogicObject {
    DriverWrapper wrapper = new DriverWrapper();
    WebDriver driver1;
    WebDriver driver2;

    @FindBy(id = "admin_login_input")
    private Input loginInput;
    @FindBy(id = "admin_password_input")
    private Input passwordInput;
    @FindBy(id = "submit_admin_login_btn")
    private Button submitAdminLogin;

    @FindBy(id = "secret_input")
    private Input secretInput;
    @FindBy(id = "submit_secrets")
    private Button submitSecrets;

    @FindBy(id = "news_button")
    private Button newsButton;

    @FindBy(id = "news_name")
    private Input NewsName;
    @FindBy(id = "news_subtitle")
    private Input newsSubtitle;

    @FindBy(id = "add-news-image")
    private Input addNewsImage;

    @FindBy(id = "admin_add_news_submit_btn")
    private Button addNewsButton;

    @FindBy(id = "to_projects")
    private Button toProjects;


    public ProductLogicObject(String[] browsers){
        PageFactory.initElements(new CombinedDecorator(getDriver(browsers[0])) ,this);
    }
    @Step
    public void initialize_and_go(String[] browsers, String url){
        wrapper.initializeDriver(browsers);
        if (wrapper.getDriver1() != null){
            driver1 = wrapper.getDriver1();
        }
        if (wrapper.getDriver2() != null){
            driver2 = wrapper.getDriver2();
        }
        if (driver1 != null){
            driver1.get(url);
        }
        if (driver2 != null){
            driver2.get(url);
        }
    }
    @Step
    public ProductLogicObject goTo(String url) {
        if (driver1 != null){
            driver1.get(url);
        }
        if (driver2 != null) {
            driver2.get(url);
        }
        if ((driver1 == null) && (driver2 == null)){
            throw new IllegalArgumentException("Can initialize just chrome and firefox now");
        }
        return this;
    }
    @Step
    public ProductLogicObject fillLogin(String name){
        Assert.assertTrue(loginInput.is_visible(),"Can not find assert element");
        loginInput.waitForInput();
        loginInput.fill(name);
        return this;
    }
    @Step
    public ProductLogicObject fillPassword(String password){
        Assert.assertTrue(passwordInput.is_visible(),"Can not find assert element");
        passwordInput.waitForInput();
        passwordInput.fill(password);
        return this;
    }
    @Step
    public  ProductLogicObject pressSubmit(){
        Assert.assertTrue(submitAdminLogin.is_visible(),"Can not find the button");
        Assert.assertTrue(submitAdminLogin.is_enable(),"Button is not available");
        submitAdminLogin.waitForButton();
        submitAdminLogin.click();
        return this;
    }
    @Step
    public ProductLogicObject fillSecrets(String secret){
        secretInput.waitForInput();
        secretInput.fill(secret);
        return this;
    }
    @Step
    public  ProductLogicObject pressSubmitSecret(){
        Assert.assertTrue(submitSecrets.is_visible(),"Can not find the button");
        Assert.assertTrue(submitSecrets.is_enable(),"Button is not available");
        submitSecrets.waitForButton();
        submitSecrets.click();
        return this;
    }

    @Step
    public ProductLogicObject goToNewsMenu(){
        Assert.assertTrue(newsButton.is_visible(),"Can not find the button");
        newsButton.waitForButton();
        newsButton.click();
        return this;
    }

    @Step
    public ProductLogicObject fillTitle(String name){
        NewsName.waitForInput();
        NewsName.fill(name);
        return this;
    }

    @Step
    public ProductLogicObject fillSubTitle(String name){
        newsSubtitle.waitForInput();
        newsSubtitle.fill(name);
        return this;
    }

    @Step
    public ProductLogicObject addNewsIMG(String path){
        String absolutePath = new File(path).getAbsolutePath();
        addNewsImage.fill(absolutePath);
        return this;
    }

    @Step
    public ProductLogicObject addNews(){
        Assert.assertTrue(addNewsButton.is_visible(),"Can not find the button");
        addNewsButton.waitForButton();
        addNewsButton.click();
        return this;
    }

    @Step
    public ProductLogicObject ScrollToFooter(){
        JavascriptExecutor js = (JavascriptExecutor) driver1;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        System.out.println("scrolled");

        return this;
    }

    @Step
    public ProductLogicObject Click_on_to_projects() throws InterruptedException {
        Assert.assertTrue(toProjects.is_visible(),"Can not find the button");
        Assert.assertTrue(toProjects.is_enable(),"Can not find the button");
        toProjects.waitForButton();
        Thread.sleep(1500);
        toProjects.click();

        return this;
    }

    @Step
    public ProductLogicObject ScrollTop(){
        JavascriptExecutor js = (JavascriptExecutor) driver1;

        js.executeScript("window.scrollTo(0, 0);");
        System.out.println("Scrolled to top of the page.");

        return this;
    }




}
