import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TaskMasterAPI {
    @Test
    public void put()  
        String payload= "{\n" +
                "    \"name\": \"tommy\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
    
                Response response= RestAssured.given().header("Content-Type", " application/json").body(payload).when().post("https://reqres.in//api/users");
                response.prettyPrint();
    
    }

    @Test
    public void get() {
        Response responseGet = RestAssured.given().when().get("https://reqres.in/api/users?page=2");
        responseGet.prettyPrint();
    }

    @Test
    public void geter() {
        Response responseGetId = RestAssured.given().when().get("https://reqres.in/api/users/2");
        responseGetId.prettyPrint();
    }

    @Test
    public void delete() {
        Response responseDel = RestAssured.given().when().delete("https://reqres.in/api/users/5");
        responseDel.prettyPrint();
    }

    @Test
    public void updae() {
        String payloadUpdate = "{\n" + "    \"name\": \"Mottu\",\n" + "    \"job\": \"president\"\n" + "}\n";

        Response responseUp = RestAssured.given().header("Content-Type", " application/json").body(payloadUpdate).when()
                .put("https://reqres.in/api/users/2");
        responseUp.prettyPrint();
    }
}}