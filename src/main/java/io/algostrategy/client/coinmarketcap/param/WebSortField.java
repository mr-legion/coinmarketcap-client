package io.algostrategy.client.coinmarketcap.param;

/**
 * The fields used to sort the data.
 */
public enum WebSortField {
    RANK("rank");

    private final String name;

    WebSortField(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}