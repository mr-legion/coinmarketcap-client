package io.algostrategy.client.coinmarketcap;

import io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiRestClientImpl;
import io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiService;
import io.algostrategy.client.coinmarketcap.impl.CoinmarketcapApiServiceGenerator;
import okhttp3.OkHttpClient;

import static io.algostrategy.client.coinmarketcap.constant.CoinmarketcapApiConstants.API_BASE_URL;

/**
 * A factory for creating API client objects.
 */
public class CoinmarketcapApiClientFactory {

    private final CoinmarketcapApiServiceGenerator serviceGenerator;

    private String apiKey;

    public CoinmarketcapApiClientFactory() {
        this.serviceGenerator = new CoinmarketcapApiServiceGenerator(new OkHttpClient());
    }

    public CoinmarketcapApiClientFactory(String apiKey) {
        this.serviceGenerator = new CoinmarketcapApiServiceGenerator(new OkHttpClient());
        this.apiKey = apiKey;
    }

    public CoinmarketcapApiClientFactory(String apiKey, ApiInteractionConfig apiInteractionConfig) {
        this(new OkHttpClient(), apiKey, apiInteractionConfig);
    }

    public CoinmarketcapApiClientFactory(OkHttpClient client,
                                         String apiKey,
                                         ApiInteractionConfig apiInteractionConfig) {
        OkHttpClient newClient = client.newBuilder()
                .proxySelector(new CustomProxySelector(apiInteractionConfig.getProxies()))
                .addInterceptor(new RateLimitInterceptor(
                        apiInteractionConfig.getMaxRequestsPerSecond(),
                        apiInteractionConfig.getMaxApiKeyUsagePerSecond()
                )).build();
        this.serviceGenerator = new CoinmarketcapApiServiceGenerator(newClient);
        this.apiKey = apiKey;
    }

    /**
     * New instance without authentication.
     *
     * @return the API client factory
     */
    public static CoinmarketcapApiClientFactory newInstance() {
        return new CoinmarketcapApiClientFactory();
    }

    /**
     * New instance with authentication.
     *
     * @return the API client factory
     */
    public static CoinmarketcapApiClientFactory newInstance(String apiKey) {
        return new CoinmarketcapApiClientFactory(apiKey);
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public CoinmarketcapApiRestClient newRestClient() {
        return new CoinmarketcapApiRestClientImpl(
                serviceGenerator.createService(CoinmarketcapApiService.class, API_BASE_URL, apiKey));
    }
}
