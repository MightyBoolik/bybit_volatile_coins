package api.tests;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DepositAddressTests {
    @Test
    public void createDepositAddress() {
        given()
                .contentType(ContentType.JSON)
                .baseUri("https://api.kucoin.com")
                .when()
                .get("/api/v1/accounts")
                .then().log().body();
    }
}