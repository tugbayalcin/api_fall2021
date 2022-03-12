package get_http_request.day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest06
{


    //https://restful-booker.herokuapp.com/booking/5 url'ine
    //accept type'i "application/json" olan GET request'i yolladigimda
    //gelen response'un
    //status kodunun 200
    //ve content type'inin "application/json"
    //ve firstname'in "Jim"
    //ve totalprice'in 600
    //ve checkin date'in 2015-06-12"oldugunu test edin
    @Test
    public void test06()
    {
        String url ="http://dummy.restapiexample.com/api/v1/employees";

        Response response = given().when().get(url);

        response.prettyPrint();

        response.then().contentType(ContentType.JSON).statusCode(200);

        response.then().assertThat().body("data", Matchers.hasSize(24)
                , "data.employee_name", Matchers.hasItem("Ashton Cox")
                , "data.employee_age", Matchers.hasItems(21, 61, 23));
    }
}
