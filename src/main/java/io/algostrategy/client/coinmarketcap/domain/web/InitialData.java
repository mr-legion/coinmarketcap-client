package io.algostrategy.client.coinmarketcap.domain.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Initial data for DEX page.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InitialData {

    @JsonProperty("exchanges")
    private List<Dexer> dexers;

    private String sort;
    private String direction;

    private Integer count;
}
