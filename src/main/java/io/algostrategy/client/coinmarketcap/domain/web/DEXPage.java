package io.algostrategy.client.coinmarketcap.domain.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DEX page data.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DEXPage {

    private InitialData initialData;

    @JsonProperty("chainPlatforms")
    private List<Network> networks;
}
