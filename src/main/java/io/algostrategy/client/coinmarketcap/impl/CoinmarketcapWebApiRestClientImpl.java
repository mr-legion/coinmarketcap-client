package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapWebApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Page;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.web.DEXPool;
import io.algostrategy.client.coinmarketcap.domain.web.Exchange;
import io.algostrategy.client.coinmarketcap.domain.web.MarketCategory;
import io.algostrategy.client.coinmarketcap.domain.web.SortField;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

import static io.algostrategy.client.coinmarketcap.domain.web.SortField.RANK;
import static io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiServiceGenerator.executeSync;
import static java.util.logging.Level.WARNING;

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
    public List<DEXPool> getAllDEXPools(Integer chainId, Integer dexId) {

        List<DEXPool> dexPools = new ArrayList<>();

        try {

            Page<List<DEXPool>> firstPage = getDEXPools(chainId, dexId, 1, RANK).getData();
            int totalPages = (int) Math.ceil((double) firstPage.getTotal() / firstPage.getCount());

            dexPools.addAll(firstPage.getData());

            for (int i = 2; i <= totalPages; i++) {
                try {
                    dexPools.addAll(getDEXPools(chainId, dexId, i, RANK).getData().getData());
                } catch (Exception e) {
                    log.log(WARNING, "Failed to load DEX pools sorted by rank, page " + i, e);
                }
            }
        } catch (Exception e) {
            log.log(WARNING, "Failed to load DEX pools sorted by rank, page " + 1, e);
        }

        return dexPools;
    }

    @Override
    public Response<Page<List<DEXPool>>> getDEXPools(Integer chainId, Integer dexId, Integer page, SortField sortField) {
        return executeSync(coinmarketcapWebApiService.getDEXPools(chainId, dexId, page, sortField));
    }
}
