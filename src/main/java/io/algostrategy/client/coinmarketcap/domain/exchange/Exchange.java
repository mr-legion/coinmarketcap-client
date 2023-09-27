package io.algostrategy.client.coinmarketcap.domain.exchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonProperty("is_active")
    private Boolean active;

    @JsonProperty("is_listed")
    private Boolean listed;

    @JsonProperty("is_redistributable")
    private Boolean redistributable;

    @JsonProperty("first_historical_data")
    private String firstHistoricalData;

    @JsonProperty("last_historical_data")
    private String lastHistoricalData;
}
