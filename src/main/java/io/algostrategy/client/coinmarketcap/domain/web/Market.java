package io.algostrategy.client.coinmarketcap.domain.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static io.algostrategy.client.coinmarketcap.constant.CoinmarketcapApiConstants.DATE_FORMAT;

/**
 * Market data.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Market {

    @JsonProperty("marketId")
    private Integer id;

    @JsonProperty("marketPair")
    private String symbol;

    private MarketCategory category;

    @JsonProperty("marketUrl")
    private String url;

    private Double marketScore;
    private Double marketReputation;

    private Boolean outlierDetected;
    private Boolean priceExcluded;
    private Boolean volumeExcluded;

    private String feeType;

    private Integer exchangeId;
    private String exchangeName;
    private String exchangeSlug;

    private Integer baseCurrencyId;
    private String baseSymbol;
    private String baseCurrencyName;
    private String baseCurrencySlug;

    private Integer quoteCurrencyId;
    private String quoteSymbol;

    private Double price;
    private Double quote;
    private Double volumeUsd;
    private Double volumeBase;
    private Double volumeQuote;

    private Double depthUsdNegativeTwo;
    private Double depthUsdPositiveTwo;
    private Double effectiveLiquidity;

    @JsonFormat(pattern = DATE_FORMAT)
    @JsonProperty("lastUpdated")
    private Date lastUpdated;
}
