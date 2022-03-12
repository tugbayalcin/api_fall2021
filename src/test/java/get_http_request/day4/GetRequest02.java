package get_http_request.day4;

import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest02
{
    @Test
    public void test02()
    {
        String url = "https://reqres.in/api/users";

        Response response = given().when().get(url);

        response.prettyPrint(); // yalnizca response body'sini getirir
        //response.prettyPeek(); // response'dakiher seyi getirir (daha detayli) header+body
        //response.then().log().all(); // bu da response'dakiher seyi getirir

        // 1.test, status code'u headers'i test ettik
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK");

        // 2.test, body'deki bilgileri test ediyoruz
        response
                .then()
                .body("data[0].first_name", Matchers.equalTo("George"),
                        "data[0].last_name", Matchers.equalTo("Bluth"),
                        "data[0].email", Matchers.equalTo("george.bluth@reqres.in"));

        // Matchers class'ini static import yaparak kullanalim, kodumuz kisalsin
        response
                .then()
                .body("data[0].first_name", equalTo("George"),
                        "data[0].last_name", equalTo("Bluth"),
                        "data[0].email", equalTo("george.bluth@reqres.in"));

        response.then().body("data[1].id", equalTo(2),
                "data[1].email",equalTo("janet.weaver@reqres.in"),
                "data[1].first_name",equalTo("Janet"),
                "data[1].last_name",equalTo("Weaver"),
                "data[1].avatar",equalTo("https://reqres.in/img/faces/2-image.jpg"));

    }
}
