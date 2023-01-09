package api.tests;

import api.ComparatorCoinsSort;
import api.pojoModels.AllTikersData;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class VolatileCoinsTests {

    public List<AllTikersData> getAllCoins() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri("https://api.kucoin.com")
                .when()
                .get("/api/v1/market/allTickers")
                .then().log().body()
                .extract().body().jsonPath().getList("data.ticker", AllTikersData.class);
    }

    @Test
    public void getUsdtPairs() {
        List<AllTikersData> usdtPairs = getAllCoins().stream()
                .filter(o -> o.getSymbol().endsWith("USDT"))
                .collect(Collectors.toList());
        Assert.assertTrue("List have other pairs besides USDT", usdtPairs.stream().allMatch(o -> o.getSymbol().endsWith("USDT")));
    }

    @Test
    public void getMaxPumped() {
        List<AllTikersData> maxVolatile = getAllCoins().stream()
                .filter(o -> o.getSymbol().endsWith("USDT"))
                .sorted(new ComparatorCoinsSort())
                .collect(Collectors.toList());
        Assert.assertFalse("List of volatile coins is empty", maxVolatile.isEmpty());
    }
}