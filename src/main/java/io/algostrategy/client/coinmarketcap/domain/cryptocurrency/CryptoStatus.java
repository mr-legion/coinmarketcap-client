package io.algostrategy.client.coinmarketcap.domain.cryptocurrency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Cryptocurrency status.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum CryptoStatus {
    ACTIVE,
    INACTIVE,
    UNTRACKED
}