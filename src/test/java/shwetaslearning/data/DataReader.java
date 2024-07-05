package shwetaslearning.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        //read JSON to String
       String jsonContent =  FileUtils.readFileToString(new File(System.getProperty("user.dir") + "/src/test/java/shwetaslearning/data/PurchaseOrder.json"), StandardCharsets.UTF_8);

       //String to HashMap by using dependency Jackson Databind(add dependency first)
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
        });
        return data;//it returns list of HashMap {map,map}
    }
}
