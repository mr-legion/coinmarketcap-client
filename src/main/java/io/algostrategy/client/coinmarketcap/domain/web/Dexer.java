package io.algostrategy.client.coinmarketcap.domain.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dexer data.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dexer {

    private Integer id;

    private Integer rank;

    private String name;
    private String slug;

    private Boolean dexStatus;

    private Integer platformId;

    private Double score;

    private Integer numMarkets;
    private Integer numCoins;

    private String type;
}
