package io.algostrategy.client.coinmarketcap;

import io.algostrategy.client.coinmarketcap.impl.*;
import okhttp3.OkHttpClient;

import static io.algostrategy.client.coinmarketcap.constant.CoinmarketcapApiConstants.API_BASE_URL;
import static io.algostrategy.client.coinmarketcap.constant.CoinmarketcapApiConstants.WEB_API_BASE_URL;
import static io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiServiceGenerator.createService;

/**
 * A factory for creating client objects.
 */
public class CoinmarketcapClientFactory {

    private final OkHttpClient client;

    private String apiKey;

    public CoinmarketcapClientFactory() {
        this.client = new OkHttpClient();
    }

    public CoinmarketcapClientFactory(String apiKey) {
        this.client = new OkHttpClient();
        this.apiKey = apiKey;
    }

    public CoinmarketcapClientFactory(OkHttpClient client, String apiKey) {
        this.client = client;
        this.apiKey = apiKey;
    }

    /**
     * New instance without authentication.
     *
     * @return the client factory
     */
    public static CoinmarketcapClientFactory newInstance() {
        return new CoinmarketcapClientFactory();
    }

    /**
     * New instance with authentication.
     *
     * @return the client factory
     */
    public static CoinmarketcapClientFactory newInstance(String apiKey) {
        return new CoinmarketcapClientFactory(apiKey);
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public CoinmarketcapApiRestClient newRestClient() {
        return new CoinmarketcapApiRestClientImpl(createCoinmarketcapApiService(client));
    }

    /**
     * Creates a new synchronous/blocking REST client with limits.
     */
    public CoinmarketcapApiRestClient newRestClient(ApiInteractionConfig apiInteractionConfig) {
        OkHttpClient newClient = applyRestriction(apiInteractionConfig);
        return new CoinmarketcapApiRestClientImpl(createCoinmarketcapApiService(newClient));
    }

    /**
     * Creates a new synchronous/blocking Web REST client.
     */
    public CoinmarketcapWebApiRestClient newWebRestClient() {
        return new CoinmarketcapWebApiRestClientImpl(createCoinmarketcapWebApiService(client));
    }

    /**
     * Creates a new synchronous/blocking Web REST client with limits.
     */
    public CoinmarketcapWebApiRestClient newWebRestClient(ApiInteractionConfig apiInteractionConfig) {
        OkHttpClient newClient = applyRestriction(apiInteractionConfig);
        return new CoinmarketcapWebApiRestClientImpl(createCoinmarketcapWebApiService(newClient));
    }

    /**
     * Creates a new synchronous/blocking Web parser.
     */
    public CoinmarketcapWebParser newWebParser() {
        return new CoinmarketcapWebParser(client);
    }

    /**
     * Create coinmarketcap api service.
     *
     * @param client client
     * @return coinmarketcap api service
     */
    private CoinmarketcapApiService createCoinmarketcapApiService(OkHttpClient client) {
        return createService(client, CoinmarketcapApiService.class, API_BASE_URL, apiKey);
    }

    /**
     * Create coinmarketcap web api service.
     *
     * @param client client
     * @return coinmarketcap web api service
     */
    private CoinmarketcapWebApiService createCoinmarketcapWebApiService(OkHttpClient client) {
        return createService(client, CoinmarketcapWebApiService.class, WEB_API_BASE_URL, apiKey);
    }

    /**
     * Apply limits to the client.
     *
     * @param apiInteractionConfig limits
     * @return new modifying client
     */
    private OkHttpClient applyRestriction(ApiInteractionConfig apiInteractionConfig) {
        return client.newBuilder()
                .proxySelector(new CustomProxySelector(apiInteractionConfig.getProxies()))
                .addInterceptor(new RateLimitInterceptor(
                        apiInteractionConfig.getMaxRequestsPerSecond(),
                        apiInteractionConfig.getMaxApiKeyUsagePerSecond()
                )).build();
    }
}
