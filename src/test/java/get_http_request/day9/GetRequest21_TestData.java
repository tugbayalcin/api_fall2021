package get_http_request.day9;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import testData.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest21_TestData extends JsonPlaceHolderBaseUrl
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
    public void test21()
    {
        // 1.Url Olustur
        spec04.pathParams("1","todos","2",2);

        // 2. Expected Data Olustur
        JsonPlaceHolderTestData expectedDataObject = new JsonPlaceHolderTestData();
        HashMap<String,Object> expectedData = (HashMap<String, Object>)  expectedDataObject.setUpTestData();
        System.out.println("Test Data'nin Icindeki Expected Data" + expectedData);

        // Server=cloudflare,
        // completed=false,
        // title=quis ut nam facilis et officia qui,
        // userId=1,
        // statusCode=200,
        // via=1.1 vegur

        // 3. Request ve Response
        Response response = given().spec(spec04).when().get("/{1}/{2}");
        response.prettyPrint();

        // 4. Dogrulama

        // 4 -->1. Yol Matcher Class
        response.then().assertThat().statusCode((Integer)expectedData.get("statusCode"));


    }
}
