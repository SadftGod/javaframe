package codes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class API_Business_Object {
    API_Product_Object po = new API_Product_Object();

    public API_Business_Object delete_new() throws JsonProcessingException {
        NewsObject cur_news = (NewsObject) po.get_all_news();

        DeleteResponse deleted = po.delete_by_id(cur_news.getData().get(cur_news.getData().size() - 1).get_id());
        System.out.println(deleted);

        return this;
    }

    public void create(String name, String category, String location, String description, String prodType, String pathToImg) throws IOException {
        File imgFile = new File(pathToImg);
        JustMessageResp resp = po.createById( name,  category,  location,  description,  prodType , imgFile);
        System.out.println(resp);



    }

    public void get_projects() throws JsonProcessingException {
        ProjectsObj cur_news = (ProjectsObj) po.get_projects();
        System.out.println(cur_news);
    }
}
