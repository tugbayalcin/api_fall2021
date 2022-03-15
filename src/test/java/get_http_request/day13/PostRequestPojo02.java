package get_http_request.day13;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;

public class PostRequestPojo02 extends HerOkuAppBaseUrl
{
    /*
 https://restful-booker.herokuapp.com/booking
 request body
 { "firstname": "Ali",
            "lastname": "Can",
            "totalprice": 500,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2022-03-01",
                "checkout": "2022-03-11"
             }
 }}
Status code is 200
 response body
 {
    "bookingid": 11,
       "booking": {
         "firstname": "Ali",
         "lastname": "Can",
         "totalprice": 500,
         "depositpaid": true,
         "bookingdates": {
            "checkin": "2022-03-01",
            "checkout": "2022-03-11"
                             }
                         }
                     }
  */

    @Test
    public void test02()
    {
        // 1. URL OLUSTUR
        spec05.pathParam("bir","booking");

        // 2. EXPECTED DATA
        BookingDatesPojo bookingDates = new BookingDatesPojo("2022-03-01","2022-03-11");
        System.out.println("bookingDates = " + bookingDates);

        BookingPojo booking = new BookingPojo("Ali","Can",500,true,bookingDates);
        System.out.println("booking = " + booking);

        //BookingResponsePojo bookingResponse = new BookingResponsePojo(11,booking);
        //System.out.println("bookingResponse = " + bookingResponse);
        // bu kismi gondermeyecegimiz icin obje olusturmaya gerek yok
        // cunku id'yi kendisi veriyordu

        // 3. REQUEST VE RESPONSE
        Response response = given().contentType(ContentType.JSON).spec(spec05).auth().basic("admin","password123")
                .body(booking)
                .when().post("/{bir}");

        response.prettyPrint();

        // internal server hatasi verdiginde

        // 4. DOGRULAMA
        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.getStatusCode());

        Assert.assertEquals(booking.getFirstname(),actualData.getBookingPojo().getFirstname());
        Assert.assertEquals(booking.getLastname(),actualData.getBookingPojo().getLastname());
        Assert.assertEquals(booking.getTotalprice(),actualData.getBookingPojo().getTotalprice());
        Assert.assertEquals(booking.isDepositpaid(),actualData.getBookingPojo().isDepositpaid());

        Assert.assertEquals(booking.getBookingDatesPojo().getCheckin(),actualData.getBookingPojo().getBookingDatesPojo().getCheckin());
        Assert.assertEquals(booking.getBookingDatesPojo().getCheckout(),actualData.getBookingPojo().getBookingDatesPojo().getCheckout());

    }
}
