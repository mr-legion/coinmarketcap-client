package io.algostrategy.client.coinmarketcap;

import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.CryptoMetadata;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.CryptoStatus;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.Cryptocurrency;
import io.algostrategy.client.coinmarketcap.domain.fiat.Currency;
import io.algostrategy.client.coinmarketcap.param.AuxiliaryField;
import io.algostrategy.client.coinmarketcap.param.SortField;

import java.util.List;
import java.util.Map;

/**
 * The API facade, supporting synchronous/blocking access REST API.
 */
public interface CoinmarketcapApiRestClient {

    // Cryptocurrency endpoints

    /**
     * Get all cryptocurrencies exclude auxiliary fields.
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

    /**
     * Get cryptocurrency metadata.
     *
     * @param ids             for displaying
     * @param skipInvalid     skip validation rules
     * @param auxiliaryFields to include auxiliary fields
     * @return cryptocurrency metadata
     */
    Response<Map<Integer, CryptoMetadata>> getCryptoMetadata(Integer[] ids,
                                                             Boolean skipInvalid,
                                                             AuxiliaryField[] auxiliaryFields);

    /**
     * Get cryptocurrency metadata.
     *
     * @param ids             for displaying
     * @param slugs           slugs to return
     * @param symbols         symbols to return
     * @param address         address to return
     * @param skipInvalid     skip validation rules
     * @param auxiliaryFields to include auxiliary fields
     * @return cryptocurrency metadata
     */
    Response<Map<Integer, CryptoMetadata>> getCryptoMetadata(Integer[] ids,
                                                             String[] slugs,
                                                             String[] symbols,
                                                             String address,
                                                             Boolean skipInvalid,
                                                             AuxiliaryField[] auxiliaryFields);

    // Currency endpoints

    /**
     * Get only currencies.
     *
     * @param start offset the start (1-based index)
     * @param limit specify the number of results, valid value: [1 .. 5000]
     * @return currencies
     */
    Response<List<Currency>> getOnlyCurrencies(Integer start, Integer limit);

    /**
     * Get currencies.
     *
     * @param start         offset the start (1-based index)
     * @param limit         specify the number of results, valid value: [1 .. 5000]
     * @param sortField     what field to sort the list
     * @param includeMetals pass true to include precious metals
     * @return currencies
     */
    Response<List<Currency>> getCurrencies(Integer start,
                                           Integer limit,
                                           SortField sortField,
                                           Boolean includeMetals);
}