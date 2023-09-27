package io.algostrategy.client.coinmarketcap.domain.fiat;

/**
 * The fields used to sort the data.
 */
public enum SortField {
    ID("id"),
    NAME("name");

    private final String name;

    SortField(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}