package codes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataProvide {
    @DataProvider(name = "jsonDataProvider")
    public Object[][] getDataFromJson() throws IOException {
        String jsonFilePath = "src/main/resources/products.json";

        ObjectMapper objectMapper = new ObjectMapper();
        List<News> dataList = objectMapper.readValue(new File(jsonFilePath), new TypeReference<List<News>>() {});

        Object[][] data = new Object[dataList.size()][3];
        for (int i = 0; i < dataList.size(); i++) {
            News testData = dataList.get(i);
            data[i][0] = testData.getName();
            data[i][1] = testData.getSubname();
            data[i][2] = testData.getPath_to_img();
        }

        return data;
    }
}
