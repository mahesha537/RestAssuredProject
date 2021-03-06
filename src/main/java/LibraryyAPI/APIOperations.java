package LibraryyAPI;

import Payloads.payload;
import Utilities.reUsables;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APIOperations {
    @Test(dataProvider = "BooksData")
    public void addBook(String author,String isbn){
        RestAssured.baseURI = "http://216.10.245.166";
        String postResponse = given().header("Content-Type","Application/Json")
                .body(payload.addBook(author,isbn))    //requires new data every time--otherwise we get 404 error
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        String ID = (reUsables.rawTojson(postResponse)).get("ID");
        Assert.assertEquals((reUsables.rawTojson(postResponse)).get("Msg"),"successfully added");
        System.out.println("ID is: "+ID);
    }

//    Sending data through dataproviders
    @DataProvider(name = "BooksData")
    public  Object[][] getData(){
//        new object[][] {Array1,Array2,....}
        return new Object[][] {{"fefe","wfg"},{"wfee","wfewf"},{"efewfvew","wefwg"}};
    }
}
