package codes;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class BusinessLogicObject {

    private final String[] browsers;
    private final ProductLogicObject po;

    public BusinessLogicObject(String[] browsers){
        this.browsers = browsers;
        this.po = new ProductLogicObject(browsers);
    }


    @Test
    public BusinessLogicObject goToWebsite(String[] browsers, String url){
        po.initialize_and_go(browsers,url);
        return this;
    }

    @Test
    public BusinessLogicObject findTheProjects() throws InterruptedException {
        po.ScrollToFooter()
                .Click_on_to_projects()
                .ScrollTop();

        return this;
    }


    @Test
    public BusinessLogicObject Login(String url,String name,String password,String secret){
        po.goTo(url)
                .fillLogin(name)
                .fillPassword(password)
                .pressSubmit()
                .fillSecrets(secret)
                .pressSubmitSecret();
        return this;
    }

    @Test
    public BusinessLogicObject CreateNews(String name,String subname,String path){
        po.goToNewsMenu()
                .fillTitle(name)
                .fillSubTitle(subname)
                .addNewsIMG(path)
                .addNews();
        return this;
    }


    @Test
    public BusinessLogicObject closeBrowsers(String[] browsers){
        DriverProvider.quitDriver(browsers[0]);
        return this;
    }

}
