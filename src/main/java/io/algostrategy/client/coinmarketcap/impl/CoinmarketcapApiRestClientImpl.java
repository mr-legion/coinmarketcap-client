package io.algostrategy.client.coinmarketcap.impl;

import com.google.common.collect.Lists;
import io.algostrategy.client.coinmarketcap.CoinmarketcapApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.*;
import io.algostrategy.client.coinmarketcap.domain.exchange.Exchange;
import io.algostrategy.client.coinmarketcap.domain.fiat.Currency;
import io.algostrategy.client.coinmarketcap.util.ArrayUtils;
import okhttp3.OkHttpClient;

import java.util.List;
import java.util.Map;

import static io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiServiceGenerator.createService;
import static io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiServiceGenerator.executeSync;

/**
 * Implementation of REST API using Retrofit with synchronous/blocking method calls.
 */
public class CoinmarketcapApiRestClientImpl implements CoinmarketcapApiRestClient {

    private final CoinmarketcapApiService coinmarketcapApiService;

    public CoinmarketcapApiRestClientImpl(String apiKey) {
        this.coinmarketcapApiService = createService(apiKey, CoinmarketcapApiService.class);
    }

    public CoinmarketcapApiRestClientImpl(String apiKey, OkHttpClient client) {
        this.coinmarketcapApiService = createService(apiKey, client, CoinmarketcapApiService.class);
    }

    // Cryptocurrency endpoints

    @Override
    public Response<List<Cryptocurrency>> getCryptosExcludeAUX(Integer start, Integer limit) {
        return getCryptos(CryptoStatus.values(), start, limit, null, null, new AuxiliaryField[0]);
    }

    @Override
    public Response<List<Cryptocurrency>> getCryptos(CryptoStatus[] cryptoStatuses,
                                                     Integer start,
                                                     Integer limit,
                                                     SortField sortField,
                                                     String[] symbols,
                                                     AuxiliaryField[] auxiliaryFields) {
        String cryptoStatusesStr = ArrayUtils.arrayToString(cryptoStatuses);
        String symbolsStr = ArrayUtils.arrayToString(symbols);
        String auxiliaryFieldsStr = ArrayUtils.arrayToString(auxiliaryFields);
        return executeSync(coinmarketcapApiService.getCryptocurrencies(
                cryptoStatusesStr, start, limit, sortField, symbolsStr, auxiliaryFieldsStr
        ));
    }

    @Override
    public Response<List<CryptoMetadata>> getCryptoMetadata(String ids,
                                                            Boolean skipInvalid,
                                                            AuxiliaryField auxiliaryField) {
        return getCryptoMetadata(ids, skipInvalid, new AuxiliaryField[]{auxiliaryField});
    }

    @Override
    public Response<List<CryptoMetadata>> getCryptoMetadata(String ids,
                                                            Boolean skipInvalid,
                                                            AuxiliaryField[] auxiliaryFields) {
        return getCryptoMetadata(ids, null, null, null, skipInvalid, auxiliaryFields);
    }

    @Override
    public Response<List<CryptoMetadata>> getCryptoMetadata(String ids,
                                                            String[] slugs,
                                                            String[] symbols,
                                                            String address,
                                                            Boolean skipInvalid,
                                                            AuxiliaryField[] auxiliaryFields) {

        String slugsStr = ArrayUtils.arrayToString(slugs);
        String symbolsStr = ArrayUtils.arrayToString(symbols);
        String auxiliaryFieldsStr = ArrayUtils.arrayToString(auxiliaryFields);

        Response<Map<Integer, CryptoMetadata>> response = executeSync(
                coinmarketcapApiService.getCryptocurrencyInfo(
                        ids, slugsStr, symbolsStr, address, skipInvalid, auxiliaryFieldsStr
                ));

        return new Response<>(Lists.newArrayList(response.getData().values()), response.getStatus());
    }

    // Currency endpoints

    @Override
    public Response<List<Currency>> getOnlyCurrencies() {
        return getCurrencies(null, null, null, false);
    }

    @Override
    public Response<List<Currency>> getCurrencies(Integer start,
                                                  Integer limit,
                                                  io.algostrategy.client.coinmarketcap.domain.fiat.SortField sortField,
                                                  Boolean includeMetals) {
        return executeSync(coinmarketcapApiService.getCurrencies(start, limit, sortField, includeMetals));
    }

    // Exchanges endpoints

    @Override
    public Response<List<Exchange>> getExchanges(Integer start, Integer limit) {
        return executeSync(coinmarketcapApiService.getExchanges(start, limit));
    }
}
