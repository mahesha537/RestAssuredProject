import Files.payload;
import Utilities.reUsables;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JSONParse {
    @Test
    public void jsonParseAssignment(){
//        Get number of courses available
        int totalcourses = reUsables.rawTojson(payload.payloadToJson()).getInt("courses.size()");
        int purchaseAmount = reUsables.rawTojson(payload.payloadToJson()).getInt("dashboard.purchaseAmount");
        System.out.println("Total courses available: "+totalcourses);
//        prnt purchase amount
        System.out.println("Purchase amount: "+purchaseAmount);
//        Title of first course
        System.out.println("First Course: "+reUsables.rawTojson(payload.payloadToJson()).get("courses[0].title"));
        int sum=0;
//        Prnt all courses and their prces
        for(int i=0;i<totalcourses;i++){
            int price = reUsables.rawTojson(payload.payloadToJson()).getInt("courses["+i+"].price");
            int copies= reUsables.rawTojson(payload.payloadToJson()).getInt("courses["+i+"].copies");
            System.out.println("Course "+(i+1)+": "+reUsables.rawTojson(payload.payloadToJson()).get("courses["+i+"].title")+" and its price: "
            +price);
            String courseTitle = reUsables.rawTojson(payload.payloadToJson()).get("courses["+i+"].title");
            if(courseTitle.equalsIgnoreCase("RPA")){
                System.out.println("Number od copies sold by RPA: "+copies);
            }
            sum = sum+(copies*price);
        }
        System.out.println("Total amount:"+sum);
        Assert.assertEquals(sum, purchaseAmount);
        System.out.println("Purchase amount is validated");

    }

}
