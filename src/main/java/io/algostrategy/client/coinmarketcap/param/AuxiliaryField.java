package io.algostrategy.client.coinmarketcap.param;

/**
 * The auxiliary fields.
 */
public enum AuxiliaryField {
    PLATFORM("platform"),
    ACTIVE("is_active"),
    STATUS("status"),
    FIRST_HISTORICAL_DATA("first_historical_data"),
    LAST_HISTORICAL_DATA("last_historical_data");

    private final String name;

    AuxiliaryField(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}