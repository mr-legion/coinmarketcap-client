package io.algostrategy.client.coinmarketcap;

import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.domain.cryptocurrency.*;
import io.algostrategy.client.coinmarketcap.domain.dex.DEXPool;
import io.algostrategy.client.coinmarketcap.domain.dex.Network;
import io.algostrategy.client.coinmarketcap.domain.exchange.Exchange;
import io.algostrategy.client.coinmarketcap.domain.fiat.Currency;

import java.util.List;

/**
 * The API facade, supporting synchronous/blocking access REST API.
 */
public interface CoinmarketcapApiRestClient {

    // Cryptocurrency endpoints

    /**
     * Get cryptocurrencies exclude auxiliary fields.
     *
     * @param start offset the start (1-based index)
     * @param limit specify the number of results, valid value: [1 .. 5000]
     * @return cryptocurrencies
     */
    Response<List<Cryptocurrency>> getCryptosExcludeAUX(Integer start, Integer limit);

    /**
     * Get cryptocurrencies.
     *
     * @param cryptoStatuses for displaying
     * @param start          offset the start (1-based index)
     * @param limit          specify the number of results, valid value: [1 .. 5000]
     * @param sortField      what field to sort the list
     * @param symbols        symbols to return
     * @param aux            to include auxiliary fields
     * @return cryptocurrencies
     */
    Response<List<Cryptocurrency>> getCryptos(CryptoStatus[] cryptoStatuses,
                                              Integer start,
                                              Integer limit,
                                              SortField sortField,
                                              String[] symbols,
                                              AuxiliaryField[] aux);

    /**
     * Get all cryptocurrency metadata. The execution takes long time and
     * some cryptocurrency metadata may be skipped if some requests fails.
     *
     * @param ids         for displaying
     * @param skipInvalid skip validation rules
     * @param aux         to include auxiliary fields
     * @return cryptocurrency metadata
     */
    Response<List<CryptoMetadata>> getCryptoMetadata(String ids,
                                                     Boolean skipInvalid,
                                                     AuxiliaryField... aux);

    /**
     * Get all cryptocurrency metadata. The execution takes long time and
     * some cryptocurrency metadata may be skipped if some requests fails.
     *
     * @param ids         for displaying
     * @param slugs       slugs to return
     * @param symbols     symbols to return
     * @param address     address to return
     * @param skipInvalid skip validation rules
     * @param aux         to include auxiliary fields
     * @return cryptocurrency metadata
     */
    Response<List<CryptoMetadata>> getCryptoMetadata(String ids,
                                                     String[] slugs,
                                                     String[] symbols,
                                                     String address,
                                                     Boolean skipInvalid,
                                                     AuxiliaryField[] aux);

    // Currency endpoints

    /**
     * Get only currencies. Start 1, limit 5000.
     *
     * @return currencies
     */
    Response<List<Currency>> getOnlyCurrencies();

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
                                           io.algostrategy.client.coinmarketcap.domain.fiat.SortField sortField,
                                           Boolean includeMetals);

    // Exchange endpoints

    /**
     * Get exchanges.
     *
     * @param start offset the start (1-based index)
     * @param limit specify the number of results, valid value: [1 .. 5000]
     * @return exchanges
     */
    Response<List<Exchange>> getExchanges(Integer start, Integer limit);

    // DEX endpoints

    /**
     * Get networks.
     *
     * @param start offset the start (1-based index)
     * @param limit specify the number of results, valid value: [1 .. 500]
     * @param aux   to include auxiliary fields
     * @return networks
     */
    Response<List<Network>> getNetworks(Integer start,
                                        Integer limit,
                                        io.algostrategy.client.coinmarketcap.domain.dex.AuxiliaryField... aux);

    /**
     * Get DEX pools.
     *
     * @param networkId network ID
     * @param limit     specify the number of results, valid value: [1 .. 100]
     * @param scrollId  is an alternative to traditional pagination techniques
     * @return DEX pools
     */
    Response<List<DEXPool>> getDEXPools(Integer networkId, Integer limit, String scrollId);

    /**
     * Get DEX pools.
     *
     * @param networkId network ID
     * @param dexerId   dexer ID
     * @param limit     specify the number of results, valid value: [1 .. 100]
     * @param scrollId  is an alternative to traditional pagination techniques
     * @return DEX pools
     */
    Response<List<DEXPool>> getDEXPools(Integer networkId, Integer dexerId, Integer limit, String scrollId);
}