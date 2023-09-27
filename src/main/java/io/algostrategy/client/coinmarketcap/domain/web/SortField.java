package io.algostrategy.client.coinmarketcap.domain.web;

/**
 * The fields used to sort the data.
 */
public enum SortField {
    RANK("rank");

    private final String name;

    SortField(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}