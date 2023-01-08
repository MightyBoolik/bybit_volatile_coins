package api;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class VolatileCoins {

    @Test
    public void getCoins(){
        given()
                .contentType(ContentType.JSON)
                .baseUri("https://api.kucoin.com")
                .when()
                .get("/api/v1/symbols")
                .then().log().body();
    }
}
