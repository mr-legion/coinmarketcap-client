package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapWebApiClientFactory;
import io.algostrategy.client.coinmarketcap.CoinmarketcapWebApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Page;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.web.DEXPool;
import io.algostrategy.client.coinmarketcap.domain.web.Exchange;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.algostrategy.client.coinmarketcap.domain.web.MarketCategory.SPOT;
import static io.algostrategy.client.coinmarketcap.param.WebSortField.RANK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CoinmarketcapWebApiRestClientImplTest {

    private final CoinmarketcapWebApiRestClient coinmarketcapWebApiRestClient =
            CoinmarketcapWebApiClientFactory.newInstance().newRestClient();

    @Test
    public void test() {
        coinmarketcapWebApiRestClient.getAllDEXPools();
    }

    @Test
    public void getMarkets_ShouldReturnMarkets() {
        Response<Exchange> response = coinmarketcapWebApiRestClient.getMarkets(270, SPOT, 1, 100);
        assertNotNull(response);
        assertThat(response.getData().getMarkets(), is(not(empty())));
    }

    @Test
    public void getDEXPools_ShouldReturnAllDEXPools() {
        Response<Page<List<DEXPool>>> response = coinmarketcapWebApiRestClient.getDEXPools(1, RANK);
        assertNotNull(response);
        assertThat(response.getData().getData(), is(not(empty())));
    }

    @Test
    public void getDEXPools_ShouldReturnDEXPools() {
        Response<Page<List<DEXPool>>> response = coinmarketcapWebApiRestClient.getDEXPools(14, 1344, 1, RANK);
        assertNotNull(response);
        assertThat(response.getData().getData(), is(not(empty())));
    }
}