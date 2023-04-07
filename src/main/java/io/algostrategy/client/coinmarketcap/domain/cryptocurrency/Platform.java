package io.algostrategy.client.coinmarketcap.domain.cryptocurrency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Metadata about the parent cryptocurrency platform.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Platform {

    private Integer id;

    private String name;
    private String symbol;
    private String slug;

    @JsonProperty("token_address")
    private String tokenAddress;
}
