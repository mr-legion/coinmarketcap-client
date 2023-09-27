package io.algostrategy.client.coinmarketcap.domain.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Network data.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Network {

    private Integer id;

    private String name;

    private String dexerPlatformName;

    @JsonProperty("cryptoId")
    private Integer assetId;

    private Boolean visibilityOnDexscan;

    private String explorerUrlFormat;
    private String addressExplorerUrl;
}
