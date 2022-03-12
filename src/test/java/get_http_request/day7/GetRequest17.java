package get_http_request.day7;

import base_url.GMIBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest17 extends GMIBankBaseUrl
{
       /*
   http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın

    {
       "firstName": "Della",
       "lastName": "Heaney",
       "email": "ricardo.larkin@yahoo.com",
       "mobilePhoneNumber": "123-456-7893",
    }
    */

    @Test
    public void test17()
    {
        // 1. URL Olusturma
        spec03.pathParams("bir","tp-customers","iki","114351");

        // 2. Expected Data Oluşturma
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstName","Della");
        expectedData.put("lastName","Heaney");
        expectedData.put("email","ricardo.larkin@yahoo.com");
        expectedData.put("mobilePhoneNumber","123-456-7893");

        System.out.println("Expected Data: " + expectedData);

        // 3. Request ve Response
        // http://www.gmibank.com/api
        // Response response = given().spec(spec03).when().get("/{bir}/{iki}");
        // bu sekilde calismaz, banka oldugu izin token kullanmak zorundayiz
        Response response = given().spec(spec03).header("Authorization", "Bearer " + generateToken()).
                when().get("/{bir}/{iki}");
        // /{bir}/{iki} --> /tp-customers/114351 anlamina geliyor, bu kismi adrese ekliyor

        response.prettyPrint();

        Map<String,Object> actualData = response.as(HashMap.class); // De-Serialization

        System.out.println("Actual Data: " + actualData);

        Assert.assertEquals(expectedData.get("firstName"),actualData.get("firstName"));
        Assert.assertEquals(expectedData.get("lastName"),actualData.get("lastName"));
        Assert.assertEquals(expectedData.get("email"),actualData.get("email"));
        Assert.assertEquals(expectedData.get("mobilePhoneNumber"),actualData.get("mobilePhoneNumber"));

    }
}
