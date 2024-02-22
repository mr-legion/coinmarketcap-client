package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.AuxiliaryField;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.CryptoMetadata;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.Cryptocurrency;
import io.algostrategy.client.coinmarketcap.domain.exchange.Exchange;
import io.algostrategy.client.coinmarketcap.domain.fiat.Currency;
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
        this.coinmarketcapApiRestClient = new CoinmarketcapApiRestClientImpl(apiKey);
    }

    @Test
    public void getCryptosExcludeAUX_ShouldReturnCryptosExcludeAUX() {
        Response<List<Cryptocurrency>> response = coinmarketcapApiRestClient.getCryptosExcludeAUX(1, 100);
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
        String ids = "1,2,3";
        AuxiliaryField[] fields = {AuxiliaryField.URLS};
        Response<List<CryptoMetadata>> response = coinmarketcapApiRestClient.getCryptoMetadata(ids, true, fields);
        assertNotNull(response.getData());
        assertThat(response.getData(), is(not(empty())));
    }

    @Test
    public void getCryptoMetadata_ShouldReturnCryptoMetadata() {
        String ids = "1,2,3";
        Response<List<CryptoMetadata>> response = coinmarketcapApiRestClient.getCryptoMetadata(
                ids, null, null, null, null, null
        );
        assertNotNull(response.getData());
        assertThat(response.getData(), is(not(empty())));
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

    @Test
    public void getExchanges_ShouldReturnExchanges() {
        Response<List<Exchange>> response = coinmarketcapApiRestClient.getExchanges(null, null);
        assertNotNull(response);
        assertThat(response.getData(), is(not(empty())));
    }
}