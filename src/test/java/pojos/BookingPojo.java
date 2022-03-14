package pojos;

public class BookingPojo
{
    // "booking": {
    //            "firstname": "Ali",
    //            "lastname": "Can",
    //            "totalprice": 500,
    //            "depositpaid": true,
    //            "bookingdates": {
    //                            "checkin": "2022-03-01",
    //                            "checkout": "2022-03-11"
    //                      }
    //                  }
    //              }

    // 1. private degiskenleri olustur
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesPojo bookingDatesPojo;

    // 2. getter ve setter methodlari olustur

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDatesPojo getBookingDatesPojo() {
        return bookingDatesPojo;
    }

    public void setBookingDatesPojo(BookingDatesPojo bookingDatesPojo) {
        this.bookingDatesPojo = bookingDatesPojo;
    }


    // 3. parametreli ve parametresiz constructor olustur

    public BookingPojo(){}

    public BookingPojo(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDatesPojo bookingDatesPojo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingDatesPojo = bookingDatesPojo;
    }


    // 4. toString Methodunu override et

    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingDatesPojo=" + bookingDatesPojo +
                '}';
    }
}
