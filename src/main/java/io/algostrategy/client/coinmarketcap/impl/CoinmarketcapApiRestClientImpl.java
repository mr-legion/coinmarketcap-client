package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.*;
import io.algostrategy.client.coinmarketcap.domain.exchange.Exchange;
import io.algostrategy.client.coinmarketcap.domain.fiat.Currency;
import io.algostrategy.client.coinmarketcap.util.ArrayUtils;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiServiceGenerator.executeSync;
import static io.algostrategy.client.coinmarketcap.util.ArrayUtils.concatIntToFixedStr;
import static java.util.logging.Level.WARNING;

/**
 * Implementation of REST API using Retrofit with synchronous/blocking method calls.
 */
@Log
public class CoinmarketcapApiRestClientImpl implements CoinmarketcapApiRestClient {

    private final CoinmarketcapApiService coinmarketcapApiService;

    public CoinmarketcapApiRestClientImpl(CoinmarketcapApiService coinmarketcapApiService) {
        this.coinmarketcapApiService = coinmarketcapApiService;
    }

    // Cryptocurrency endpoints

    @Override
    public List<Cryptocurrency> getAllCryptosExcludeAUX() {

        List<Cryptocurrency> cryptos = new ArrayList<>();

        for (int start = 1, limit = 5000; ; start += limit) {

            List<Cryptocurrency> currCryptos = getCryptosExcludeAUX(start, limit).getData();
            cryptos.addAll(currCryptos);

            if (currCryptos.size() < limit) {
                break;
            }
        }

        return cryptos;
    }

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
    public List<CryptoMetadata> getCryptoMetadata(Integer[] ids,
                                                  Boolean skipInvalid,
                                                  AuxiliaryField[] auxiliaryFields) {
        return getCryptoMetadata(ids, null, null, null, skipInvalid, auxiliaryFields);
    }

    @Override
    public List<CryptoMetadata> getCryptoMetadata(Integer[] ids,
                                                  String[] slugs,
                                                  String[] symbols,
                                                  String address,
                                                  Boolean skipInvalid,
                                                  AuxiliaryField[] auxiliaryFields) {

        List<String> idPacks = concatIntToFixedStr(ids, 1500);
        String slugsStr = ArrayUtils.arrayToString(slugs);
        String symbolsStr = ArrayUtils.arrayToString(symbols);
        String auxiliaryFieldsStr = ArrayUtils.arrayToString(auxiliaryFields);

        List<CryptoMetadata> cryptoMetadataList = new ArrayList<>();
        for (String idPack : idPacks) {
            try {
                Response<Map<Integer, CryptoMetadata>> response = executeSync(
                        coinmarketcapApiService.getCryptocurrencyInfo(
                                idPack, slugsStr, symbolsStr, address, skipInvalid, auxiliaryFieldsStr
                        ));
                cryptoMetadataList.addAll(response.getData().values());
            } catch (Exception e) {
                log.log(WARNING, "Failed to load crypto metadata", e);
            }
        }

        return cryptoMetadataList;
    }

    // Currency endpoints

    @Override
    public Response<List<Currency>> getOnlyCurrencies(Integer start, Integer limit) {
        return executeSync(coinmarketcapApiService.getCurrencies(start, limit, null, false));
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
