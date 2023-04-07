package io.algostrategy.client.coinmarketcap.domain.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Exchange data.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exchange {

    private Integer id;

    private String name;
    private String slug;

    @JsonProperty("numMarketPairs")
    private Integer numMarkets;

    @JsonProperty("marketPairs")
    private List<Market> markets;
}
