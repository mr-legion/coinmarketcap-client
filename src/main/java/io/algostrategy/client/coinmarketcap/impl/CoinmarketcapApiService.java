package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.Cryptocurrency;
import io.algostrategy.client.coinmarketcap.param.SortField;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

import static io.algostrategy.client.coinmarketcap.constant.CoinmarketcapApiConstants.AUTHORIZATION_REQUIRED_HEADER;

/**
 * REST API URL mappings.
 */
public interface CoinmarketcapApiService {

    // Cryptocurrency endpoints

    @Headers(AUTHORIZATION_REQUIRED_HEADER)
    @GET("/v1/cryptocurrency/map")
    Call<Response<List<Cryptocurrency>>> getCryptocurrencies(
            @Query(value = "listing_status", encoded = true) String cryptoStatuses,
            @Query("start") Integer start,
            @Query("limit") Integer limit,
            @Query("sort") SortField sortField,
            @Query(value = "symbol", encoded = true) String symbols,
            @Query(value = "aux", encoded = true) String aux);
}
