package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapWebApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.web.Exchange;
import io.algostrategy.client.coinmarketcap.domain.web.MarketCategory;

import static io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiServiceGenerator.executeSync;

/**
 * Implementation of Web REST API using Retrofit with synchronous/blocking method calls.
 */
public class CoinmarketcapWebApiRestClientImpl implements CoinmarketcapWebApiRestClient {

    private final CoinmarketcapWebApiService coinmarketcapWebApiService;

    public CoinmarketcapWebApiRestClientImpl(CoinmarketcapWebApiService coinmarketcapWebApiService) {
        this.coinmarketcapWebApiService = coinmarketcapWebApiService;
    }

    // Exchange endpoints

    @Override
    public Response<Exchange> getMarkets(Integer exchangeId, MarketCategory category, Integer start, Integer limit) {
        return executeSync(coinmarketcapWebApiService.getMarkets(exchangeId, category, start, limit));
    }
}
