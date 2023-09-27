package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.CryptoMetadata;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.Cryptocurrency;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.SortField;
import io.algostrategy.client.coinmarketcap.domain.exchange.Exchange;
import io.algostrategy.client.coinmarketcap.domain.fiat.Currency;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;
import java.util.Map;

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

    @Headers(AUTHORIZATION_REQUIRED_HEADER)
    @GET("/v2/cryptocurrency/info")
    Call<Response<Map<Integer, CryptoMetadata>>> getCryptocurrencyInfo(
            @Query(value = "id", encoded = true) String ids,
            @Query(value = "slug", encoded = true) String slugs,
            @Query(value = "symbol", encoded = true) String symbols,
            @Query("address") String address,
            @Query("skip_invalid") Boolean skipInvalid,
            @Query(value = "aux", encoded = true) String aux);

    // Fiat endpoints
    @Headers(AUTHORIZATION_REQUIRED_HEADER)
    @GET("/v1/fiat/map")
    Call<Response<List<Currency>>> getCurrencies(@Query("start") Integer start,
                                                 @Query("limit") Integer limit,
                                                 @Query("sort") io.algostrategy.client.coinmarketcap.domain.fiat.SortField sortField,
                                                 @Query("include_metals") Boolean includeMetals);

    // Exchange endpoints
    @Headers(AUTHORIZATION_REQUIRED_HEADER)
    @GET("/v1/exchange/map")
    Call<Response<List<Exchange>>> getExchanges(@Query("start") Integer start,
                                                @Query("limit") Integer limit);
}
