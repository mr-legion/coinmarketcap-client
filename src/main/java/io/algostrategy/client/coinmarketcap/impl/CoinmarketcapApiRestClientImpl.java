package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.CryptoMetadata;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.CryptoStatus;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.Cryptocurrency;
import io.algostrategy.client.coinmarketcap.domain.fiat.Currency;
import io.algostrategy.client.coinmarketcap.param.AuxiliaryField;
import io.algostrategy.client.coinmarketcap.param.SortField;
import io.algostrategy.client.coinmarketcap.util.ArrayUtils;

import java.util.List;
import java.util.Map;

import static io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiServiceGenerator.executeSync;

/**
 * Implementation of REST API using Retrofit with synchronous/blocking method calls.
 */
public class CoinmarketcapApiRestClientImpl implements CoinmarketcapApiRestClient {

    private final CoinmarketcapApiService coinmarketcapApiService;

    public CoinmarketcapApiRestClientImpl(CoinmarketcapApiService coinmarketcapApiService) {
        this.coinmarketcapApiService = coinmarketcapApiService;
    }

    // Cryptocurrency endpoints

    @Override
    public Response<List<Cryptocurrency>> getAllCryptosExcludeAUX(Integer start, Integer limit) {
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
    public Response<Map<Integer, CryptoMetadata>> getCryptoMetadata(Integer[] ids, AuxiliaryField[] auxiliaryFields) {
        return getCryptoMetadata(ids, null, null, null, null, auxiliaryFields);
    }

    @Override
    public Response<Map<Integer, CryptoMetadata>> getCryptoMetadata(Integer[] ids,
                                                                    String[] slugs,
                                                                    String[] symbols,
                                                                    String address,
                                                                    Boolean skipInvalid,
                                                                    AuxiliaryField[] auxiliaryFields) {
        String idsStr = ArrayUtils.arrayToString(ids);
        String slugsStr = ArrayUtils.arrayToString(slugs);
        String symbolsStr = ArrayUtils.arrayToString(symbols);
        String auxiliaryFieldsStr = ArrayUtils.arrayToString(auxiliaryFields);
        return executeSync(coinmarketcapApiService.getCryptocurrencyInfo(
                idsStr, slugsStr, symbolsStr, address, skipInvalid, auxiliaryFieldsStr
        ));
    }

    // Currency endpoints

    @Override
    public Response<List<Currency>> getOnlyCurrencies(Integer start, Integer limit) {
        return executeSync(coinmarketcapApiService.getCurrencies(start, limit, null, false));
    }

    @Override
    public Response<List<Currency>> getCurrencies(Integer start,
                                                  Integer limit,
                                                  SortField sortField,
                                                  Boolean includeMetals) {
        return executeSync(coinmarketcapApiService.getCurrencies(start, limit, sortField, includeMetals));
    }
}
