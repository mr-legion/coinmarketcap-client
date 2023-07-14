package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.domain.Page;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.web.DEXPool;
import io.algostrategy.client.coinmarketcap.domain.web.Exchange;
import io.algostrategy.client.coinmarketcap.domain.web.MarketCategory;
import io.algostrategy.client.coinmarketcap.param.WebSortField;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

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

    @GET("/dexer/v3/platformpage/pair-pages")
    Call<Response<Page<List<DEXPool>>>> getDEXPools(@Query("platform-id") Integer chainId,
                                                    @Query("dexer-id") Integer dexId,
                                                    @Query("page") Integer page,
                                                    @Query("sort-field") WebSortField sortField);
}
