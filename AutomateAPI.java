package com.rest.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class AutomateAPI {

        public String getToken;

        @Test
        public void authToken() {
                String payload = "{\n" + "    \"username\" : \"admin\",\n" + "    \"password\" : \"password123\"\n"
                                + "}";
                Response response = given().baseUri("https://reqres.in/api/users").contentType("application/json")
                                .body(payload).when().post("/auth").then().log().all().extract().response();
                getToken = response.jsonPath().getString("token");
                System.out.println("Token: " + getToken);
        }

        // get all booking ids
        @Test
        public void getBookingIds() {
                int bookingID = given().baseUri("https://reqres.in/api/users").contentType("application/json").when()
                                .get("/booking").then().extract().response().path("bookingid[0]");
                System.out.println("BookingId: " + bookingID);
                // assert using Hamcrest
                assertThat(bookingID, equalTo(14));
                // assert using TestNG
                Assert.assertEquals(bookingID, 14);
        }

        // create booking and validate response body using assertion
        @Test
        public void createBooking() {
                String payload = "{\n" + "    \"firstname\" : \"Tommy\",\n" + "    \"lastname\" : \"Sada\",\n"
                                + "    \"totalprice\" : 111,\n" + "    \"depositpaid\" : true,\n"
                                + "    \"bookingdates\" : {\n" + "        \"checkin\" : \"2019-01-01\",\n"
                                + "        \"checkout\" : \"2020-01-01\"\n" + "    },\n"
                                + "    \"additionalneeds\" : \"Breakfast\"\n" + "}";
                given().baseUri("https://reqres.in/api/users").contentType("application/json").body(payload).when()
                                .post("/booking").then().log().all().assertThat().statusCode(200)
                                .body("booking.firstname", equalTo("tommy"), "booking.lastname", equalTo("sada"),
                                                "booking.totalprice", equalTo(111));
        }

        @Test
        public void updateBooking() {
                String payload = "{\n" + "    \"firstname\" : \"mani\",\n" + "    \"lastname\" : \"poha\",\n"
                                + "    \"totalprice\" : 55,\n" + "    \"depositpaid\" : true,\n"
                                + "    \"bookingdates\" : {\n" + "        \"checkin\" : \"2019-01-01\",\n"
                                + "        \"checkout\" : \"2020-01-01\"\n" + "    },\n"
                                + "    \"additionalneeds\" : \"Breakfast\"\n" + "}";
                given().baseUri("https://reqres.in/api/users").contentType("application/json").body(payload)
                                .header("Cookie", "token=" + getToken).log().all().when().put("/booking/1").then().log()
                                .all().assertThat().statusCode(200);
        }

        // partial update booking
        @Test
        public void partialUpdateBooking() {
                String payload = "{\n" + "    \"firstname\" : \"CA\",\n" + "    \"lastname\" : \"Preetham\"\n" + "}";
                given().baseUri("https://reqres.in/api/users").contentType("application/json")
                                .header("Cookie", "token=" + getToken).body(payload).log().all().when()
                                .patch("/booking/19").then().log().all().assertThat().statusCode(200);
        }

        @Test
        public void deleteBooking() {
                given().baseUri("https://reqres.in/api/users").contentType("application/json")
                                .header("Cookie", "token=" + getToken).log().all().when().delete("/booking/17").then()
                                .log().all().assertThat().statusCode(201);
        }

    @Test
    public void validateStatusCode(){
        given().
                baseUri("https://reqres.in/api/users").
                contentType("application/json").
       when().
                get("/booking").
       then().
                assertThat().
                statusCode(200);
    }