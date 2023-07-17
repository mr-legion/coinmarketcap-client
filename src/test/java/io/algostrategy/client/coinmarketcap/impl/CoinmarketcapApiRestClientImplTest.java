package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapApiClientFactory;
import io.algostrategy.client.coinmarketcap.CoinmarketcapApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.CryptoMetadata;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.Cryptocurrency;
import io.algostrategy.client.coinmarketcap.domain.fiat.Currency;
import io.algostrategy.client.coinmarketcap.param.AuxiliaryField;
import org.hamcrest.collection.IsMapWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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

    @Test
    public void getCryptoMetadata_ShouldReturnCryptoMetadataIncludeAUX() {
        Integer[] ids = {1, 2, 3};
        AuxiliaryField[] fields = {AuxiliaryField.URLS};
        Response<Map<Integer, CryptoMetadata>> response = coinmarketcapApiRestClient.getCryptoMetadata(ids, true, fields);
        assertNotNull(response);
        assertThat(response.getData(), allOf(notNullValue(), is(not(IsMapWithSize.anEmptyMap()))));
    }

    @Test
    public void getCryptoMetadata_ShouldReturnCryptoMetadata() {
        Integer[] ids = {1, 2, 3};
        Response<Map<Integer, CryptoMetadata>> response = coinmarketcapApiRestClient.getCryptoMetadata(
                ids, null, null, null, null, null
        );
        assertNotNull(response);
        assertThat(response.getData(), allOf(notNullValue(), is(not(IsMapWithSize.anEmptyMap()))));
    }

    @Test
    public void getOnlyCurrencies_ShouldReturnCurrencies() {
        Response<List<Currency>> response = coinmarketcapApiRestClient.getOnlyCurrencies(null, null);
        assertNotNull(response);
        assertThat(response.getData(), is(not(empty())));
    }

    @Test
    public void getCurrencies_ShouldReturnCurrencies() {
        Response<List<Currency>> response = coinmarketcapApiRestClient.getCurrencies(null, null, null, null);
        assertNotNull(response);
        assertThat(response.getData(), is(not(empty())));
    }
}