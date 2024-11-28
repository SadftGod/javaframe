package codes;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners({AlureListener.class})
public class Start {
    // UI Tests
    @Test
    void logining() {
        try {
            String[] browsers = {"chrome"};
            String url = "http://localhost:3000/";
            String loginPath = "/loginpanel";

            adminCreds ac = new adminCreds();

            new BusinessLogicObject(browsers)

                    // Go to local website
                    .goToWebsite(browsers,url)

                    // Redirect to admin menu
                    .Login(Utils.joinPaths(url,loginPath),ac.getName(),ac.getPassword(),ac.getSecret())

                    // Close browser in finish
                    .closeBrowsers(browsers)
            ;
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
    @Test(dataProvider = "jsonDataProvider", dataProviderClass = DataProvide.class)
    void creteNews(String name, String subname, String pathToImg){
        try{
            String[] browsers = {"chrome"};
            String url = "http://localhost:3000/";
            String loginPath = "/loginpanel";

            adminCreds ac = new adminCreds();

            new BusinessLogicObject(browsers)
                    // Go to local website
                    .goToWebsite(browsers,url)

                    // Redirect to admin menu
                    .Login(Utils.joinPaths(url,loginPath),ac.getName(),ac.getPassword(),ac.getSecret())

                    // Redirect to creating news
                    .CreateNews(name,subname,pathToImg)

                    // Close browser in finish
                    .closeBrowsers(browsers)

            ;

        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


    }
    @Test
    void findTheProjects() throws InterruptedException {
        String[] browsers = {"chrome"};
        String url = "http://localhost:3000/";

        new BusinessLogicObject(browsers)
                // Go to local website
                .goToWebsite(browsers,url)
                .findTheProjects()

                //Close the browser
                .closeBrowsers(browsers)

        ;
    }

    // API tests
    @Test
    void delete_new() throws JsonProcessingException {
        new API_Business_Object()
                .delete_new();
    }

    @Test(dataProvider = "ProjectProvider", dataProviderClass = ProjectProvider.class)
    void createProject(String name, String category, String location, String description, String prodType, String pathToImg) throws IOException {
        new API_Business_Object().create(name, category, location, description, prodType, pathToImg);
    }
//    on all system out of linux have coding text problem

    @Test
    void get_projects() throws JsonProcessingException {
        new API_Business_Object().get_projects();

    }



}
