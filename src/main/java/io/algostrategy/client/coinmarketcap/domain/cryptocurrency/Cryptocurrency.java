package io.algostrategy.client.coinmarketcap.domain.cryptocurrency;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static io.algostrategy.client.coinmarketcap.constant.CoinmarketcapApiConstants.DATE_FORMAT;

/**
 * Cryptocurrency data.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cryptocurrency {

    private Integer id;

    private Integer rank;

    private String name;
    private String symbol;
    private String slug;

    @JsonProperty("is_active")
    private Boolean active;

    private CryptoStatus status;

    private Platform platform;

    @JsonFormat(pattern = DATE_FORMAT)
    @JsonProperty("first_historical_data")
    private Date firstHistoricalData;

    @JsonFormat(pattern = DATE_FORMAT)
    @JsonProperty("last_historical_data")
    private Date lastHistoricalData;
}
