package io.algostrategy.client.coinmarketcap.domain.cryptocurrency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Cryptocurrency category.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum CryptoCategory {
    @JsonProperty("coin")
    COIN,
    @JsonProperty("token")
    TOKEN
}