package io.algostrategy.client.coinmarketcap.domain.dex;

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
    @JsonProperty("network_slug")
    private String slug;

    private String alternativeName;

    @JsonProperty("cryptocurrencyId")
    private Integer cryptoId;

    private Integer wrappedTokenId;
    private String wrappedTokenSlug;

    private String tokenExplorerUrl;
    private String poolExplorerUrl;
    private String transactionHashUrl;
}
