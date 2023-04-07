package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapApiClientFactory;
import io.algostrategy.client.coinmarketcap.CoinmarketcapApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.Cryptocurrency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CoinmarketcapApiRestClientImplTest {

    private CoinmarketcapApiRestClient coinmarketcapApiRestClient;

    @BeforeEach
    public void setUp() {
        String apiKey = System.getenv("API_KEY");
        this.coinmarketcapApiRestClient = CoinmarketcapApiClientFactory.newInstance(apiKey).newRestClient();
    }

    @Test
    public void getAllCryptosExcludeAUX_ShouldReturnAllCryptosExcludeAUX() {
        Response<List<Cryptocurrency>> response = coinmarketcapApiRestClient.getAllCryptosExcludeAUX(1, 100);
        assertNotNull(response);
        assertThat(response.getData(), is(not(empty())));
    }

    @Test
    public void getCryptocurrencies_ShouldReturnCryptocurrencies() {
        Response<List<Cryptocurrency>> response = coinmarketcapApiRestClient.getCryptos(
                null, null, null, null, null, null
        );
        assertNotNull(response);
        assertThat(response.getData(), is(not(empty())));
    }
}