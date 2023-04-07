package io.algostrategy.client.coinmarketcap;

import io.algostrategy.client.coinmarketcap.impl.*;
import okhttp3.OkHttpClient;

import static io.algostrategy.client.coinmarketcap.constant.CoinmarketcapApiConstants.WEB_API_BASE_URL;

/**
 * A factory for creating Web API client objects.
 */
public class CoinmarketcapWebApiClientFactory {

    private final CoinmarketcapApiServiceGenerator serviceGenerator;

    public CoinmarketcapWebApiClientFactory() {
        this.serviceGenerator = new CoinmarketcapApiServiceGenerator(new OkHttpClient());
    }

    public CoinmarketcapWebApiClientFactory(ApiInteractionConfig apiInteractionConfig) {
        this(new OkHttpClient(), apiInteractionConfig);
    }

    public CoinmarketcapWebApiClientFactory(OkHttpClient client, ApiInteractionConfig apiInteractionConfig) {
        OkHttpClient newClient = client.newBuilder()
                .proxySelector(new CustomProxySelector(apiInteractionConfig.getProxies()))
                .addInterceptor(new RateLimitInterceptor(
                        apiInteractionConfig.getMaxRequestsPerSecond(),
                        apiInteractionConfig.getMaxApiKeyUsagePerSecond()
                )).build();
        this.serviceGenerator = new CoinmarketcapApiServiceGenerator(newClient);
    }

    /**
     * New instance without authentication.
     *
     * @return the API client factory
     */
    public static CoinmarketcapWebApiClientFactory newInstance() {
        return new CoinmarketcapWebApiClientFactory();
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public CoinmarketcapWebApiRestClient newRestClient() {
        return new CoinmarketcapWebApiRestClientImpl(
                serviceGenerator.createService(CoinmarketcapWebApiService.class, WEB_API_BASE_URL));
    }
}
