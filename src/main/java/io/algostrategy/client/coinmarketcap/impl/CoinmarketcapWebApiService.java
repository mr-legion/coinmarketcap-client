package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.web.Exchange;
import io.algostrategy.client.coinmarketcap.domain.web.MarketCategory;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Web REST API URL mappings.
 */
public interface CoinmarketcapWebApiService {

    // Exchange endpoints

    @GET("/data-api/v3/exchange/market-pairs/latest")
    Call<Response<Exchange>> getMarkets(@Query("id") Integer exchangeId,
                                        @Query("category") MarketCategory category,
                                        @Query("start") Integer start,
                                        @Query("limit") Integer limit);
}
