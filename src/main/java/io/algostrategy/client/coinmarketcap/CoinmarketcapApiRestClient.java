package io.algostrategy.client.coinmarketcap;

import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.CryptoStatus;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.Cryptocurrency;
import io.algostrategy.client.coinmarketcap.param.AuxiliaryField;
import io.algostrategy.client.coinmarketcap.param.SortField;

import java.util.List;

/**
 * The API facade, supporting synchronous/blocking access REST API.
 */
public interface CoinmarketcapApiRestClient {

    // Cryptocurrency endpoints

    /**
     * Get all cryptocurrencies without auxiliary fields.
     *
     * @param start offset the start (1-based index)
     * @param limit specify the number of results, valid value: [1 .. 5000]
     * @return cryptocurrencies
     */
    Response<List<Cryptocurrency>> getAllCryptosExcludeAUX(Integer start, Integer limit);

    /**
     * Get cryptocurrencies.
     *
     * @param cryptoStatuses  for displaying
     * @param start           offset the start (1-based index)
     * @param limit           specify the number of results, valid value: [1 .. 5000]
     * @param sortField       what field to sort the list
     * @param symbols         symbols to return
     * @param auxiliaryFields to include auxiliary fields
     * @return cryptocurrencies
     */
    Response<List<Cryptocurrency>> getCryptos(CryptoStatus[] cryptoStatuses,
                                              Integer start,
                                              Integer limit,
                                              SortField sortField,
                                              String[] symbols,
                                              AuxiliaryField[] auxiliaryFields);
}