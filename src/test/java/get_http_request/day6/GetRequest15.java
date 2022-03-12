package get_http_request.day6;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest15 extends GMIBankBaseUrl
{

    /*
         https://www.gmibank.com/api/tp-customers/85694
        "login": "dino.kohler",
        "firstName": "Winona",
        "lastName": "Abernathy",
        "email": "winonaabernathy@gmail.com"
     */
    @Test
    public void test15()
    {
        spec03.pathParams("bir","tp-customers","iki", "85694");

        // buraya kadar base usl'im http://www.gmibank.com/api idi
        Response response = given()
                .spec(spec03)
                .header("Authorization", "Bearer " + generateToken())
                .when().get("/{bir}/{iki}"); // sonuna /tp-customers/114351 eklemis olduk

        response.prettyPrint();

        // Matchers Class Ile
        response.then().body("user.login", equalTo("dino.kohler")
        ,"user.firstName", equalTo("Winona")
        ,"user.lastName", equalTo("Abernathy")
        ,"user.email", equalTo("winonaabernathy@gmail.com"));

        // Json Path Ile
        JsonPath json = response.jsonPath();
        Assert.assertEquals("dino.kohler", json.get("user.login"));
        Assert.assertEquals("Winona", json.get("firstName"));
        Assert.assertEquals("Abernathy", json.get("lastName"));
        Assert.assertEquals("winonaabernathy@gmail.com", json.get("email"));
    }
}
