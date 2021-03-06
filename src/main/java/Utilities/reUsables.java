package Utilities;

import io.restassured.path.json.JsonPath;

import java.nio.file.Files;
import java.nio.file.Path;

public class reUsables {
    public static JsonPath rawTojson(String rawResponse){
        JsonPath js = new JsonPath(rawResponse);
        return js;
    }
    public static String convertJsonToString(Path path){
        try {
            return new String(Files.readAllBytes(path));
        }catch (java.io.IOException e){
            throw new RuntimeException("File not Found at path: "+path);
        }
    }
}
