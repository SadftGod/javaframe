package codes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class API_Product_Object {
    static {
        RestAssured.baseURI = "http://localhost:8000";
    }

    ObjectMapper om = new ObjectMapper();

    public  NewsObject get_all_news() throws JsonProcessingException {
        Response response = RestAssured.given()
                .get("/getNews");

        Assert.assertEquals(response.statusCode(), 200, "Getting news failed");


        return om.readValue(response.getBody().asString(), NewsObject.class);
    }

    public DeleteResponse delete_by_id(Integer id) throws JsonProcessingException {
        String requestBody = String.format("{\"_id\": %d}", id);
        Response response = RestAssured.given()
                .body(requestBody)
                .header("Content-Type", "application/json")
                .delete("/deleteNews");
        Assert.assertEquals(response.statusCode(), 200, "Delete news is failed");
        System.out.println(response.getBody().asString());

        return om.readValue(response.getBody().asString(), DeleteResponse.class);
    }

    public JustMessageResp createById(String name, String category, String location, String description, String prodType, File imgFile) throws JsonProcessingException {


        try {

            Response response = RestAssured.given()
                    .log().all()
                    .multiPart("title", new String(name.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))
                    .multiPart("location", new String(location.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))
                    .multiPart("category", new String(category.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))
                    .multiPart("description", new String(description.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))
                    .multiPart("prod_type", new String(prodType.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))
                    .multiPart("image", imgFile)
                    .header("Content-Type", "multipart/form-data; charset=UTF-8")
                    .post("/createProject");


            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to add project: " + response.getBody().asString());
            }

            return om.readValue(response.getBody().asString(), JustMessageResp.class);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating project: " + e.getMessage(), e);
        }
    }

    public ProjectsObj get_projects() throws JsonProcessingException {
        Response response = RestAssured.given()
                .get("/getProjects");

        Assert.assertEquals(response.statusCode(), 200, "Getting news failed");


        return om.readValue(response.getBody().asString(), ProjectsObj.class);
    }
}
