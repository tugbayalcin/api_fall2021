package get_http_request.day6;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest11
{
    String endPoint = "http://www.gmibank.com/api/tp-customers";
    String bearerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtdXJhdHRhbmMiLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTY0NjUwNTU1NH0.ySbwAfpdtEWsWMucCoyBB5ND9Cu1jyD5rwNLSqF6GQu0XfM0LwCg6PerxCkbJCVFJ7CslucH5VOWEenRK2HQ7w";

    @Test
    public void test11()
    {
        Response response = given()
                .header("Authorization","Bearer " + bearerToken) // burada bearer'dan sonra 1 bosluk var, GMI'in kendince olusturdugu bir guvenlik onlemi
                .when().get(endPoint).then().extract().response();

        response.prettyPrint();

        // bearier token'dan bir harf sil ve yanlis oldugunda nasil bir hata aldigini incele
    }
}
