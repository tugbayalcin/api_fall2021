package pojos;

public class DummyPojo {


    /*
GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                       Status code is 200
{
"status": "success",
"data": {
"id": 1,
"employee_name": "Tiger Nixon",
"employee_salary": 320800,
"employee_age": 61,
"profile_image": ""
},
"message": "Successfully! Record has been fetched."
}

*/
    //1)
    private String status;
    private Data data;
    private String message;

    //2)

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //3)

    public DummyPojo() {
    }

    public DummyPojo(String status, Data data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    //4) toString()

    @Override
    public String toString() {
        return "DummyPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
