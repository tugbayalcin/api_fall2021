package get_http_request.day8;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest20  extends JsonPlaceHolderBaseUrl
{
     /*
    https://jsonplaceholder.typicode.com/todos/2
    1) Status kodunun 200,
    2) respose body'de,
             "completed": değerinin false
             "title": değerinin "quis ut nam facilis et officia qui"
             "userId" sinin 1 ve
        header değerlerinden
             "via" değerinin "1.1 vegur" ve
             "Server" değerinin "cloudflare" olduğunu test edin…
    */

    @Test
    public void test20()
    {
        // 1.Url Olustur
        spec04.pathParams("parametre1","todos","parametre2",2);

        // 2.Expected
        HashMap<String,Object> expectedData = new HashMap<>();

        expectedData.put("statusCode",200);
        expectedData.put("completed",false);
        expectedData.put("userId",1);
        expectedData.put("via","1.1 vegur");
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("Server","cloudflare");

        // 3. Request ve Response
        Response response = given().accept(ContentType.JSON).spec(spec04).when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        //4-DOGRULAMA
        //   1.YOL MATCHERS CLASS
        //1. YOL MATCHERS CALASS
        response.then().assertThat()
                .statusCode((Integer) expectedData.get("statusCode"))
                .headers("via", equalTo(expectedData.get("via")),
                        "Server", equalTo(expectedData.get("Server")))
                .body("userId", equalTo(expectedData.get("userId")),
                        "title", equalTo(expectedData.get("title")),
                        "completed", equalTo(expectedData.get("completed")));
        //2. YOL JSON PATH
        JsonPath json= response.jsonPath();
        Assert.assertEquals(expectedData.get("statusCode"),response.statusCode());
        Assert.assertEquals(expectedData.get("via"),response.getHeader("via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));

        //bundan sonraki kısmı json formatında yazabiliriz
        Assert.assertEquals(expectedData.get("userId"), ((JsonPath) json).getInt("userId"));
        Assert.assertEquals(expectedData.get("title"),json.get("title"));
        Assert.assertEquals(expectedData.get("completed"),json.get("completed"));

        //3.YOL DE-SERIALİAZATION
        HashMap<String,Object> actualData = response.as(HashMap.class);//json formatını java'da kullanabilecegim formata cevirmis oldum
        System.out.println(actualData);
        //Json formatında calistridigimizda via ve server kısmı gelmiyor o yuzden bundan sonraki kısımda onları sorgulamıyoruz
        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));


    }
}
