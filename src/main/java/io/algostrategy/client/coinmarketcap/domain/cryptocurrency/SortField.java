package io.algostrategy.client.coinmarketcap.domain.cryptocurrency;

/**
 * The fields used to sort the data.
 */
public enum SortField {
    ID("id"),
    RANK("cmc_rank");

    private final String name;

    SortField(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}