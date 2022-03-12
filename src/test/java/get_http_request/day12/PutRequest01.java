package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PutRequest01 extends JsonPlaceHolderBaseUrl
{
    /*
https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde

{
"userId": 21,
"title": "Wash the dishes",
"completed": false
}
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
"userId": 21,
"title": "Wash the dishes",
"completed": false,
"id": 198
}
*/

    @Test
    public void test01()
    {
        // eklenecek veriyi jsonPlaceHolderTestData Class'ina eklemem gerekiyor

        // 1. URL OLUSTUR
        spec04.pathParams("bir","todos","iki","198");

        // 2. EXPECTED DATA
        JsonPlaceHolderTestData testObject = new JsonPlaceHolderTestData();
        JSONObject expectedRequest = testObject.setUpPutData();
        System.out.println("expectedRequest = " + expectedRequest);

        // 3. REQUEST VE RESPONSE
        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec04)
                .body(expectedRequest.toString()) // json object'te toString yapiyoruz map'lerde yapmiyoruz
                .when()
                .put("/{bir}/{iki}");

        response.prettyPrint();

        // 44. DOGRULAMA

        // 1. Json Path
        JsonPath json = response.jsonPath();
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedRequest.getInt("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedRequest.getString("title"),json.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),json.getBoolean("completed"));

        // 2. De-Serialization
        HashMap<String,Object> actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedRequest.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedRequest.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedRequest.get("completed"),actualData.get("completed"));
    }
}
