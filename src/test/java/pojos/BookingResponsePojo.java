package pojos;

public class BookingResponsePojo
{
    //  {
    //    "bookingid": 11,                                                    --> Burasi BookingResponsePojo'da tanimlandi
    //          "booking": {
    //                      "firstname": "Ali",                               --> burasi BookingPojo'da tanimlandi
    //                      "lastname": "Can",
    //                      "totalprice": 500,
    //                      "depositpaid": true,
    //                      "bookingdates": {
    //                                       "checkin": "2022-03-01",         --> burasi BookingDatesPojo'da tanimlandi
    //                                       "checkout": "2022-03-11"
    //                                      }
    //                       }
    //  }

    // 1. private degiskenleri olustur
    private int bookingid;
    private BookingPojo bookingPojo;

    // 2. getter ve setter methodlar

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBookingPojo() {
        return bookingPojo;
    }

    public void setBookingPojo(BookingPojo bookingPojo) {
        this.bookingPojo = bookingPojo;
    }


    // 3. parametreli ve parametresiz constructor


    public BookingResponsePojo() {}

    public BookingResponsePojo(int bookingid, BookingPojo bookingPojo) {
        this.bookingid = bookingid;
        this.bookingPojo = bookingPojo;
    }


    // 4. toString methodu

    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingid=" + bookingid +
                ", bookingPojo=" + bookingPojo +
                '}';
    }
}
