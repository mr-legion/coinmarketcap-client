package io.algostrategy.client.coinmarketcap;

import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.web.Exchange;
import io.algostrategy.client.coinmarketcap.domain.web.MarketCategory;

/**
 * The API facade, supporting synchronous/blocking access Web REST API.
 */
public interface CoinmarketcapWebApiRestClient {

    // Exchange endpoints

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
}