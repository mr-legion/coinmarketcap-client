package io.algostrategy.client.coinmarketcap.domain.cryptocurrency;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import static io.algostrategy.client.coinmarketcap.constant.CoinmarketcapApiConstants.DATE_FORMAT;

/**
 * Cryptocurrency metadata.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoMetadata {

    private Integer id;

    private String name;
    private String symbol;
    private String slug;

    private CryptoCategory category;

    private String logo;
    private String notice;
    private String description;

    private Platform platform;

    @JsonProperty("contract_address")
    private List<TokenContract> tokenContracts;

    private Urls urls;

    private List<String> tags;

    @JsonProperty("self_reported_tags")
    private List<String> selfReportedTags;

    @JsonProperty("self_reported_circulating_supply")
    private Double selfReportedCirculatingSupply;

    @JsonProperty("self_reported_market_cap")
    private Double selfReportedMarketCap;

    @JsonFormat(pattern = DATE_FORMAT)
    @JsonProperty("date_added")
    private Date dateAdded;

    @JsonFormat(pattern = DATE_FORMAT)
    @JsonProperty("date_launched")
    private Date dateLaunched;
}
