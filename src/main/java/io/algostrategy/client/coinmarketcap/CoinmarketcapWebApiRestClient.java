package io.algostrategy.client.coinmarketcap;

import io.algostrategy.client.coinmarketcap.domain.Page;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.web.DEXPool;
import io.algostrategy.client.coinmarketcap.domain.web.Exchange;
import io.algostrategy.client.coinmarketcap.domain.web.Market;
import io.algostrategy.client.coinmarketcap.domain.web.MarketCategory;
import io.algostrategy.client.coinmarketcap.param.WebSortField;

import java.util.List;

/**
 * The API facade, supporting synchronous/blocking access Web REST API.
 */
public interface CoinmarketcapWebApiRestClient {

    // Exchange endpoints

    /**
     * Get markets for exchanges. The execution takes long time and
     * some markets may be skipped if some requests fails.
     *
     * @param exchangeIds for displaying
     * @param category    of market
     * @return markets
     */
    List<Market> getMarkets(List<Integer> exchangeIds, MarketCategory category);

    /**
     * Get exchange markets.
     *
     * @param exchangeId for displaying
     * @param category   of market
     * @param start      offset the start (1-based index)
     * @param limit      specify the number of results, valid value: [1 .. 1000]
     * @return markets
     */
    Response<Exchange> getMarkets(Integer exchangeId, MarketCategory category, Integer start, Integer limit);

    /**
     * Get all existing DEX pools. The execution takes long time and
     * some pools may be skipped if some requests fails.
     *
     * @return DEX pools
     */
    List<DEXPool> getAllDEXPools();

    /**
     * Get DEX pools.
     *
     * @param page      page number
     * @param sortField what field to sort the list
     * @return DEX pools
     */
    Response<Page<List<DEXPool>>> getDEXPools(Integer page, WebSortField sortField);

    /**
     * Get DEX pools.
     *
     * @param chainId   chain ID
     * @param dexId     DEX ID
     * @param page      page number
     * @param sortField what field to sort the list
     * @return DEX pools
     */
    Response<Page<List<DEXPool>>> getDEXPools(Integer chainId, Integer dexId, Integer page, WebSortField sortField);
}