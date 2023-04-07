package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapApiRestClient;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.CryptoStatus;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.Cryptocurrency;
import io.algostrategy.client.coinmarketcap.param.AuxiliaryField;
import io.algostrategy.client.coinmarketcap.param.SortField;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        String comaSeparator = ",";

        String cryptoStatusesStr = null;
        if (cryptoStatuses != null) {
            cryptoStatusesStr = Arrays.stream(cryptoStatuses)
                    .map(cryptoStatus -> cryptoStatus.toString().toLowerCase())
                    .collect(Collectors.joining(comaSeparator));
        }

        String symbolsStr = null;
        if (symbols != null) {
            symbolsStr = String.join(comaSeparator, symbols);
        }

        String auxiliaryFieldsStr = null;
        if (auxiliaryFields != null) {
            auxiliaryFieldsStr = Arrays.stream(auxiliaryFields)
                    .map(auxiliaryField -> auxiliaryField.toString().toLowerCase())
                    .collect(Collectors.joining(comaSeparator));
        }

        return executeSync(coinmarketcapApiService.getCryptocurrencies(
                cryptoStatusesStr, start, limit, sortField, symbolsStr, auxiliaryFieldsStr
        ));
    }
}
