package io.algostrategy.client.coinmarketcap.domain.dex;

/**
 * The auxiliary fields.
 */
public enum AuxiliaryField {
    ALTERNATIVE_NAME("alternativeName"),
    CRYPTO_ID("cryptocurrencyId"),
    CRYPTO_SLUG("cryptocurrenySlug"),
    WRAPPED_TOKEN_ID("wrappedTokenId"),
    WRAPPED_TOKEN_SLUG("wrappedTokenSlug"),
    TOKEN_EXPLORER_URL("tokenExplorerUrl"),
    POOL_EXPLORER_URL("poolExplorerUrl"),
    TRANSACTION_HASH_URL("transactionHashUrl");

    private final String name;

    AuxiliaryField(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}