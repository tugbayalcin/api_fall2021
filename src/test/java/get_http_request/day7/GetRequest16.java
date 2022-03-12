package get_http_request.day7;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest16 extends JsonPlaceHolderBaseUrl
{
    /*
   https://jsonplaceholder.typicode.com/todos/7

   {
   "userId": 1,
   "id": 7,
   "title": "illo expedita consequatur quia in",
   "completed": false
    }
    */

    @Test
    public void test16()
    {
        // 1. URL Olusturma
        spec04.pathParams("bir","todos","iki",7);

        // 2. Expected Data Oluşturma
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",7);
        expectedData.put("title","illo expedita consequatur quia in");
        expectedData.put("completed",false);

        System.out.println("Expected Data: " + expectedData);

        // 3. Request ve Response
        // https://jsonplaceholder.typicode.com
        Response response = given().spec(spec04).when().get("/{bir}/{iki}");
        // /{bir}/{iki} --> /todos/7 anlamina geliyor, bu kismi adrese ekliyor

        response.prettyPrint();

        // Datayi Jason'dan Java'ya donusturme islemine DE-SERIALIZATION
        // Datayi Java'dan Jason'a donusturme islemine SERIALIZATION

        // Json to Java --> DE-SERIALIZATION
        // Java to Json --> SERIALIZATION

        Map<String,Object> actualData = response.as(HashMap.class); // De-Serialization

        System.out.println("Actual Data: " + actualData);

        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));





    }
}
