package codes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProjectProvider {

    @DataProvider(name = "ProjectProvider")
    public Object[][] getDataFromJson() throws IOException {
        String jsonFilePath = "src/main/resources/projects.json";

        ObjectMapper objectMapper = new ObjectMapper();
        List<Project> dataList = objectMapper.readValue(new File(jsonFilePath), new TypeReference<List<Project>>() {});

        Object[][] data = new Object[dataList.size()][6];
        for (int i = 0; i < dataList.size(); i++) {
            Project project = dataList.get(i);
            data[i][0] = project.getName();
            data[i][1] = project.getCategory();
            data[i][2] = project.getLocation();
            data[i][3] = project.getDescription();
            data[i][4] = project.getProd_type();
            data[i][5] = project.getPath_to_img();
        }

        return data;
    }
}
