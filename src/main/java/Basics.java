import Files.payload;
import Utilities.reUsables;
import io.restassured.RestAssured;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {
    public static void main(String[] args) {
//        Add Place for Google Maps API
      /*  given: all input details
        when: submit the api  -- resource goes here
        Then: validate the response*/
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String postResponse = given().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payload.AddPlace())
                    .when().post("maps/api/place/add/json")
                    .then().log().all().assertThat().statusCode(200)
                    .body("scope",equalTo("APP"))
                    .header("server","Apache/2.4.18 (Ubuntu)").assertThat()
                    .extract().response().asString();
        System.out.println(postResponse);
        String place_ID = reUsables.rawTojson(postResponse).get("place_id");
        System.out.println("Place_id from response: "+place_ID);
//        Add place-->update place --> get and validate new place in the repsonse
//        update place
        String new_Adress = "70 winter walk, USA";
        given().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payload.updatePlace(place_ID,new_Adress))
                .when().put("/maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg",equalTo(
                        "Address successfully updated"));

//        get place
        String getResponse = given().queryParam("key","qaclick123").queryParam("place_id",place_ID).header("Content-Type","application/json")
                          .when().get("/maps/api/place/get/json")
                            .then().assertThat().log().all().statusCode(200)
                            .body("address",equalTo(new_Adress)).extract().response().asString();
        String updatedResonseAddress = reUsables.rawTojson(getResponse).get("address");
        Assert.assertEquals(updatedResonseAddress,new_Adress);
        System.out.println("Address is updated");



    }
}
