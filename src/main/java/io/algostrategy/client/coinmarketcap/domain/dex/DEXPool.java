package io.algostrategy.client.coinmarketcap.domain.dex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.algostrategy.client.coinmarketcap.domain.converter.StringDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DEX pool information.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DEXPool {

    @JsonDeserialize(using = StringDeserializer.class)
    @JsonProperty("scroll_id")
    private String scrollId;

    @JsonProperty("name")
    private String symbol;

    @JsonProperty("contract_address")
    private String address;

    @JsonProperty("network_id")
    private Integer networkId;
    @JsonProperty("network_slug")
    private String networkSlug;

    @JsonProperty("dex_id")
    private Integer dexId;
    @JsonProperty("dex_slug")
    private String dexSlug;

    @JsonProperty("base_asset_id")
    private String baseAssetId;
    @JsonProperty("base_asset_ucid")
    private String baseAssetUcid;
    @JsonProperty("base_asset_name")
    private String baseAssetName;
    @JsonProperty("base_asset_symbol")
    private String baseAssetSymbol;
    @JsonProperty("base_asset_contract_address")
    private String baseAssetAddress;

    @JsonProperty("quote_asset_id")
    private String quoteAssetId;
    @JsonProperty("quote_asset_ucid")
    private String quoteAssetUcid;
    @JsonProperty("quote_asset_name")
    private String quoteAssetName;
    @JsonProperty("quote_asset_symbol")
    private String quoteAssetSymbol;
    @JsonProperty("quote_asset_contract_address")
    private String quoteAssetAddress;

    private Object quote;

    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("created_at")
    private String createdAt;
}
