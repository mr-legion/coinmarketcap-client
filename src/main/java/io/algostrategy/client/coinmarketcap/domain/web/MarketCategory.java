package io.algostrategy.client.coinmarketcap.domain.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Market category.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum MarketCategory {
    @JsonProperty("spot")
    SPOT,
    @JsonProperty("derivatives")
    DERIVATIVES,
    @JsonProperty("otc")
    OTC;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}