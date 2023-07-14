package io.algostrategy.client.coinmarketcap.domain.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DEX pool information.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DEXPool {

    @JsonProperty("poolId")
    private Integer id;

    @JsonProperty("platformId")
    private Integer chainId;
    @JsonProperty("platformName")
    private String chainName;

    @JsonProperty("dexerId")
    private Integer dexId;
    @JsonProperty("dexerName")
    private String dexName;

    @JsonProperty("dexerPlatformName")
    private String dexChainName;

    @JsonProperty("platformCryptoId")
    private Integer chainCryptoId;

    @JsonProperty("pairContractAddress")
    private String poolContractAddress;

    private String baseTokenSymbol;
    private String baseTokenName;
    private String baseCurrencyName;
    private String baseTokenAddress;

    @JsonProperty("quotoTokenSymbol")
    private String quoteTokenSymbol;
    @JsonProperty("quotoTokenName")
    private String quoteTokenName;
    @JsonProperty("quotoTokenAddress")
    private String quoteTokenAddress;

    private Double priceUsd;
    private Double priceQuote;

    private Double volumeUsd24h;

    private Double basePrice1h;
    private Double quotePrice1h;

    private Double baseChange24h;
    private Double quoteChange24h;

    private Double fdv;

    private Double liquidity;
    private Double liquidityScore;

    private Integer txns24h;

    private String marketUrl;

    private Boolean reverseOrder;

    private Double rank;
}
