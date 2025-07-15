package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.AuxiliaryField;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.CryptoMetadata;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.Cryptocurrency;
import io.algostrategy.client.coinmarketcap.domain.dex.DEXPool;
import io.algostrategy.client.coinmarketcap.domain.dex.Network;
import io.algostrategy.client.coinmarketcap.domain.exchange.Exchange;
import io.algostrategy.client.coinmarketcap.domain.fiat.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.algostrategy.client.coinmarketcap.domain.cryptocurrency.AuxiliaryField.URLS;
import static io.algostrategy.client.coinmarketcap.domain.dex.AuxiliaryField.CRYPTO_ID;
import static io.algostrategy.client.coinmarketcap.domain.dex.AuxiliaryField.WRAPPED_TOKEN_ID;
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

    // Cryptocurrency endpoints

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
    public void getCryptoMetadata_ShouldReturnCryptoMetadataIncludeField() {
        String ids = "1,2,3";
        Response<List<CryptoMetadata>> response = coinmarketcapApiRestClient.getCryptoMetadata(ids, true, URLS);
        assertNotNull(response.getData());
        assertThat(response.getData(), is(not(empty())));
    }

    @Test
    public void getCryptoMetadata_ShouldReturnCryptoMetadataIncludeFields() {
        String ids = "1,2,3";
        AuxiliaryField[] fields = {URLS};
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

    // Fiat endpoints

    @Test
    public void getOnlyCurrencies_ShouldReturnCurrencies() {
        Response<List<Currency>> response = coinmarketcapApiRestClient.getOnlyCurrencies();
        assertNotNull(response);
        assertThat(response.getData(), is(not(empty())));
    }

    @Test
    public void getCurrencies_ShouldReturnCurrencies() {
        Response<List<Currency>> response = coinmarketcapApiRestClient.getCurrencies(null, null, null, null);
        assertNotNull(response);
        assertThat(response.getData(), is(not(empty())));
    }

    // Exchange endpoints

    @Test
    public void getExchanges_ShouldReturnExchanges() {
        Response<List<Exchange>> response = coinmarketcapApiRestClient.getExchanges(null, null);
        assertNotNull(response);
        assertThat(response.getData(), is(not(empty())));
    }

    // DEX endpoints

    @Test
    public void getNetworks_ShouldReturnNetworksIncludeField() {
        Response<List<Network>> response = coinmarketcapApiRestClient.getNetworks(1, 10, CRYPTO_ID, WRAPPED_TOKEN_ID);
        assertNotNull(response.getData());
        assertThat(response.getData(), is(not(empty())));
    }

    @Test
    public void getDEXPools_ShouldReturnDEXPools_WhenUsingNetworkId() {
        Response<List<DEXPool>> response = coinmarketcapApiRestClient.getDEXPools(1, 10, null);
        assertNotNull(response);
        assertThat(response.getData(), is(not(empty())));
    }

    @Test
    public void getDEXPools_ShouldReturnDEXPools_WhenUsingNetworkIdAndDexerId() {
        Response<List<DEXPool>> response = coinmarketcapApiRestClient.getDEXPools(14, 1344, 10, null);
        assertNotNull(response);
        assertThat(response.getData(), is(not(empty())));
    }
}