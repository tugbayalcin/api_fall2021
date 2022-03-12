package get_http_request.day8;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest19 extends DummyBaseUrl
{
    /*
    http://dummy.restapiexample.com/api/v1/employees
    1) Status kodunun 200,
    2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
    3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
    4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
         ve bunların içerisinde "Charde Marshall" olduğunu test edin
    */

    @Test
    public void test19()
    {
        // 1. Url Olusturmak
        spec02.pathParams("1","api","2","v1","3","employees");

        //  http://dummy.restapiexample.com/api requestten onceki adresimiz
        Response response = given().spec(spec02).when().get("/{1}/{2}/{3}");
        // /{1}/{2}/{3} --> /api/v1/employees
        // http://dummy.restapiexample.com/api/v1/employees

        response.prettyPrint();

        // 1. Status code'un 200 oldugunu kontrol etmeliyiz
        Assert.assertEquals(200,response.statusCode());
        // ayni seyi assert.that().statusCode ile de yapabiliriz
        response.then().assertThat().statusCode(200);

        // 429 hata kodu alirsan, kodlarin yanlıs demek degildir,
        // yogun kullanimdan dolayi site geri donuste sikinti yapmistir demektir
        // yeniden calistir

        // 2. 10'dan buyuk tum id'leri ekrana yazdirin ve 10'dan buyuk 14 id oldugunu dogrulayin
        JsonPath json = response.jsonPath();
        List<Integer> idList = json.getList("data.findAll{it.id>10}"); // groovy komutu yazdik icine
        // List<Integer> idList = json.getList("data.findAll{it>10}"); bu sekilde de calisir
        System.out.println("ID List: " + idList);
        // GroovyJava Platformu uzerinde calisan bir bilgisayar dilidir

        // 3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu tets edin
        List<Integer>ageList=json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(ageList);

        Collections.sort(ageList);

        // Assert.assertTrue(ageList.get(ageList.size()-1)==23); bu sekilde yapilabilir
        // AssertEqual ile yapalim
        Assert.assertEquals(23,(int)ageList.get(ageList.size()-1)); // data casting istedi 1. casting yolu: narrowing
        Assert.assertEquals((Integer) 23,ageList.get(ageList.size()-1)); // data casting istedi 2. casting yolu: widening


        // 4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
        // ve bunların içerisinde "Charde Marshall" olduğunu test edin

        List<Integer>salaryList=json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println("maas listesi: " + salaryList);

        Assert.assertTrue(salaryList.contains("Charde Marshall"));







    }
}
