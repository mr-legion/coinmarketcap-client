package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapWebApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Page;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.web.DEXPool;
import io.algostrategy.client.coinmarketcap.domain.web.Exchange;
import io.algostrategy.client.coinmarketcap.domain.web.MarketCategory;
import io.algostrategy.client.coinmarketcap.domain.web.SortField;
import lombok.extern.java.Log;

import java.util.List;

import static io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiServiceGenerator.executeSync;

/**
 * Implementation of Web REST API using Retrofit with synchronous/blocking method calls.
 */
@Log
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

    @Override
    public Response<Page<List<DEXPool>>> getDEXPools(Integer chainId, Integer dexId, Integer page, SortField sortField) {
        return executeSync(coinmarketcapWebApiService.getDEXPools(chainId, dexId, page, sortField));
    }
}
