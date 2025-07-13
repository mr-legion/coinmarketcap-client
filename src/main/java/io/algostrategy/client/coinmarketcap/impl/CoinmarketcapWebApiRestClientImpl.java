package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapWebApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.web.Exchange;
import io.algostrategy.client.coinmarketcap.domain.web.MarketCategory;
import lombok.extern.java.Log;
import okhttp3.OkHttpClient;

import static io.algostrategy.client.coinmarketcap.impl.CoinmarketcapWebApiServiceGenerator.createService;
import static io.algostrategy.client.coinmarketcap.impl.CoinmarketcapWebApiServiceGenerator.executeSync;

/**
 * Implementation of Web REST API using Retrofit with synchronous/blocking method calls.
 */
@Log
public class CoinmarketcapWebApiRestClientImpl implements CoinmarketcapWebApiRestClient {

    private final CoinmarketcapWebApiService coinmarketcapWebApiService;

    public CoinmarketcapWebApiRestClientImpl() {
        this.coinmarketcapWebApiService = createService(CoinmarketcapWebApiService.class);
    }

    public CoinmarketcapWebApiRestClientImpl(OkHttpClient client) {
        this.coinmarketcapWebApiService = createService(client, CoinmarketcapWebApiService.class);
    }

    // Exchange endpoints

    @Override
    public Response<Exchange> getMarkets(Integer exchangeId, MarketCategory category, Integer start, Integer limit) {
        return executeSync(coinmarketcapWebApiService.getMarkets(exchangeId, category, start, limit));
    }
}
