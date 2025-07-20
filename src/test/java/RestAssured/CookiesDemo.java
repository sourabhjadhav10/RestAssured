package RestAssured;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CookiesDemo {

    @Test(priority = 1)
    void testCookies(){

        given()


                .when()
                .get("https://www.google.com/")



                .then()
                .cookie("AEC","AZ6Zc-U9ebvXeFScses67lcfmy2Csz-crM6BGXnr8oi5UV2XPnV39-JJEA")
                .log().all();
    }


    // capture cookie information

    @Test(priority = 2)
    void getCookiesInfo(){

        Response res=given()

                    .when()
                    .get("https://www.google.com/");

        //get single cookie info
//        String AEC_Cookie=res.getCookie("AEC");
//        System.out.println("AEC cookie value is:  " +AEC_Cookie);

        // get all cookies info
        Map<String,String> allCookies =res.getCookies();
        for (String k:allCookies.keySet()){
            String cookie_value= res.getCookie(k);
            System.out.println(k+"    :  "+cookie_value);
        }
    }
}
