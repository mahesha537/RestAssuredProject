package Utilities;

import io.restassured.path.json.JsonPath;

public class reUsables {
    public static JsonPath rawTojson(String rawResponse){
        JsonPath js = new JsonPath(rawResponse);
        return js;
    }
}
