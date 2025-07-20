package RestAssured;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class headersDemo {

    //@Test(priority = 1)
    void testHeaders(){

        given()


                .when()
                .get("https://www.google.com/")



                .then()
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .header("Content-Encoding","gzip")
                .header("Server","gws")
                .log().all();
    }

    @Test(priority = 2)
    void getHeaders(){

        Response res=given()


                .when()
                .get("https://www.google.com/");

        //get single header info
//       String header_value= res.getHeader("Content-Type");
//       System.out.println(header_value);

        // get all headers info

        Headers myHeaders=res.getHeaders();
        for (Header hd:myHeaders){
            System.out.println("Name of header: "+hd.getName()+",  value of header: "+hd.getValue());
        }

    }
}
