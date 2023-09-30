package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapWebApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Page;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.web.*;
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
    public List<Market> getMarkets(List<Integer> exchangeIds, MarketCategory category) {

        List<Market> markets = new ArrayList<>();

        for (Integer exchangeId : exchangeIds) {
            try {
                for (int start = 1, limit = 1000; ; start += limit) {

                    Response<Exchange> response = getMarkets(exchangeId, category, start, limit);
                    markets.addAll(response.getData().getMarkets());

                    if (response.getData().getNumMarkets() <= (start + limit - 1)) {
                        break;
                    }
                }
            } catch (Exception e) {
                log.log(WARNING, "Failed to load markets from exchange " + exchangeId, e);
            }
        }

        return markets;
    }

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
