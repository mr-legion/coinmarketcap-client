package io.algostrategy.client.coinmarketcap.constant;

import lombok.experimental.UtilityClass;

/**
 * Constants used throughout API.
 */
@UtilityClass
public class CoinmarketcapApiConstants {

    /**
     * Base domain.
     */
    public static final String BASE_DOMAIN = "coinmarketcap.com";

    /**
     * REST API base URL.
     */
    public static final String API_BASE_URL = "https://pro-api." + BASE_DOMAIN;

    /**
     * Web REST API base URL.
     */
    public static final String WEB_API_BASE_URL = "https://api." + BASE_DOMAIN;

    /**
     * Decorator to indicate that an endpoint requires authorization.
     */
    public static final String AUTHORIZATION_REQUIRED = "AUTHORIZATION";
    public static final String AUTHORIZATION_REQUIRED_HEADER = AUTHORIZATION_REQUIRED + ": #";

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
}
