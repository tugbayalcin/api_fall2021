package get_http_request.day14;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.path.json.JsonPath;

public class GetRequestObjectMapper01 extends JsonPlaceHolderBaseUrl
{
    // https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
    //Dönen response ’un status kodunun 200 ve body kısmının
    // {
    // “userId”: 10,
    // “id”: 198,
    // “title”: “quis eius est sint explicabo”,
    // “completed”: true
    // }
    //Olduğunu Object Mapper kullanarak test edin

    @Test
    public void test()
    {
        // 1. URL OLUSTUR
        spec04.pathParams("param1","todos","param2",198);

        // 2. EXPECTED DATA
        String jsonData = "{\n" +
                "     \"userId\": 10,\n" +
                "     \"id\": 198,\n" +
                "     \"title\": \"quis eius est sint explicabo\",\n" +
                "     \"completed\": true\n" +
                "}";

        Map<String,Object> expectedData = JsonUtil.convertJsonToJava(jsonData, Map.class);
        //JsonUtil.convertJsonToJava(jsonData, HashMap.class);  --> HashMap de yazailiyorum, kabul ediyor
        //JsonUtil.convertJsonToJava(jsonData, List.class);  --> List de yazailiyorum, kabul ediyor

        System.out.println("jsonData = " + jsonData);
        System.out.println("expectedData = " + expectedData);

        // 3. REQUEST VE RESPONSE
        Response response = given().contentType(ContentType.JSON).spec(spec04)
                .when().get("/{param1}/{param2}");

        response.prettyPrint();

        // 4. DOGRULAMA
        // De-Serialization
        // Onceki derslerde ogrendigimiz yontem
        HashMap<String,Object> actualData2 = response.as(HashMap.class);
        System.out.println("actualData2 = " + actualData2);


        // JsonUtil reusable class'ini kullanarak de-serialization yaptik
        Map<String ,Object> actualData = JsonUtil.convertJsonToJava(response.asString(),Map.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.get("userId"),actualData2.get("userId"));
        Assert.assertEquals(expectedData.get("idd"),actualData2.get("idd"));
        Assert.assertEquals(expectedData.get("title"),actualData2.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData2.get("completed"));

        // -------

        Map<String,Object> expectedData9 =  JsonUtil.convertJsonToJava(jsonData, LinkedHashMap.class);
        Map<String,Object> actualData9 = response.as(LinkedHashMap.class);
        Assert.assertEquals(expectedData9,actualData9);
        // bu sekilde kolayca hepsini dogrulayabilirsin, KISAYOL
        // ----------

        //MActher ile
        response.then().
                assertThat().
                statusCode(200).
                body("userId",equalTo(expectedData.get("userId"))
                        ,"id",equalTo(expectedData.get("id"))
                        ,"title",equalTo(expectedData.get("title"))
                        ,"completed",equalTo(expectedData.get("completed")));


        //JsonPath ile
        JsonPath json = response.jsonPath();
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.get("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedData.get("id"),json.getInt("id"));
        Assert.assertEquals(expectedData.get("title"),json.getString("title"));
        Assert.assertEquals(expectedData.get("completed"),json.getBoolean("completed"));


    }
}
