package RestAssured;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Day2 {
    //Different ways to create post request body

    //1 by using hash map
    //@Test(priority = 1)
    void testPostUsingHashmap(){
        HashMap data=new HashMap();
        data.put("name","Scott");
        data.put("location","France");
        data.put("phone","224833787");

        String courseArr[]={"nodeJs","react"};
        data.put("courses",courseArr);

        given()
                .contentType("application/json")
                .body(data)

        .when()
                .post("http://localhost:3000/Students")

        .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("224833787"))
                .body("courses[0]",equalTo("nodeJs"))
                .body("courses[1]",equalTo("react"))
                .header("Content-Type","application/json")
                .log().all();

    }

    // 2 by using org.json library

    //@Test(priority = 1)
    void testPostUsingOrgJson(){

        JSONObject data= new JSONObject();
        data.put("name","Scott");
        data.put("phone","739536356");;
        data.put("location","Delhi");
        String[] courses={"npm","maven"};
        data.put("courses",courses);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/Students")

                .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location",equalTo("Delhi"))
                .body("phone",equalTo("739536356"))
                .body("courses[0]",equalTo("npm"))
                .body("courses[1]",equalTo("maven"));

    }


    //3 using POJO (Plain Old Java Object)

   // @Test(priority = 1)
    void usingPOJO(){

        POJO_PostRequest data=new POJO_PostRequest();
        data.setName("Jhon");
        data.setLocation("USA");
        data.setPhone("835936794");
        String[] courses={"Angular","React"};
        data.setCourses(courses);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/Students")

                .then()
                .statusCode(201)
                .body("name",equalTo("Jhon"))
                .body("location",equalTo("USA"))
                .body("phone",equalTo("835936794"))
                .body("courses[0]",equalTo("Angular"))
                .body("courses[1]",equalTo("React"))
                .log().all();

    }


    //4 test post using external json file
    @Test(priority = 1)
    void testPostUsingExternalJsonFile() throws FileNotFoundException {

        File file= new File(".\\src\\test\\java\\RestAssured\\data.json");
        FileReader fileReader=new FileReader(file);
        JSONTokener jsonTokener=new JSONTokener(fileReader);
        JSONObject jsonObject=new JSONObject(jsonTokener);

        given()
                .contentType("application/json")
                .body(jsonObject.toString())

                .when()
                .post("http://localhost:3000/Students")

                .then()
                .statusCode(201)
                .body("name",equalTo("Jhon"))
                .body("location",equalTo("USA"))
                .body("phone",equalTo("835936794"))
                .body("courses[0]",equalTo("Angular"))
                .body("courses[1]",equalTo("React"))
                .log().all();

    }



    //delete record
    @Test(priority = 2)
    void deleteRecord(){
        given()

                .when()
                .delete("http://localhost:3000/Students/4")

                .then()
                .statusCode(200);

    }
}
