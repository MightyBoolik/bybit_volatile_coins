package api;

import api.pojoModels.AllTikersData;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class VolatileCoins {

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
                .filter(o->o.getSymbol().endsWith("USDT"))
                .collect(Collectors.toList());
    Assert.assertTrue("List have other pairs besides USDT",usdtPairs.stream().allMatch(o->o.getSymbol().endsWith("USDT")));
    }

    @Test
    public void getMaxPumped(){
        List<Object> maxVolatile = getAllCoins().stream()
                .filter(o->o.getSymbol().endsWith("USDT"))
                .sorted(new Comparator<AllTikersData>() {
            @Override
            public int compare(AllTikersData o1, AllTikersData o2) {
                return o2.getChangeRate().compareTo(o1.getChangeRate());
            }
        })
                .collect(Collectors.toList());
    }
}