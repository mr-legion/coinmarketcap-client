package io.algostrategy.client.coinmarketcap;

import io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiRestClientImpl;
import io.algostrategy.client.coinmarketcap.impl.CoinmarketcapWebApiRestClientImpl;
import io.algostrategy.client.coinmarketcap.impl.CoinmarketcapWebParser;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;

import static io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiServiceGenerator.createCoinmarketcapApiService;
import static io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiServiceGenerator.createCoinmarketcapWebApiService;

/**
 * The factory for creating client objects.
 */
@UtilityClass
public class CoinmarketcapClientFactory {

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public static CoinmarketcapApiRestClient newRestClient(String apiKey) {
        OkHttpClient client = new OkHttpClient();
        return new CoinmarketcapApiRestClientImpl(createCoinmarketcapApiService(client, apiKey));
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public static CoinmarketcapApiRestClient newRestClient(OkHttpClient client, String apiKey) {
        return new CoinmarketcapApiRestClientImpl(createCoinmarketcapApiService(client, apiKey));
    }

    /**
     * Creates a new synchronous/blocking Web REST client.
     */
    public static CoinmarketcapWebApiRestClient newWebRestClient() {
        OkHttpClient client = new OkHttpClient();
        return new CoinmarketcapWebApiRestClientImpl(createCoinmarketcapWebApiService(client));
    }

    /**
     * Creates a new synchronous/blocking Web REST client.
     */
    public static CoinmarketcapWebApiRestClient newWebRestClient(OkHttpClient client) {
        return new CoinmarketcapWebApiRestClientImpl(createCoinmarketcapWebApiService(client));
    }

    /**
     * Creates a new synchronous/blocking Web parser.
     */
    public static CoinmarketcapWebParser newWebParser() {
        OkHttpClient client = new OkHttpClient();
        return new CoinmarketcapWebParser(client);
    }

    /**
     * Creates a new synchronous/blocking Web parser.
     */
    public static CoinmarketcapWebParser newWebParser(OkHttpClient client) {
        return new CoinmarketcapWebParser(client);
    }
}
