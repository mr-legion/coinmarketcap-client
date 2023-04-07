package io.algostrategy.client.coinmarketcap.param;

/**
 * The auxiliary fields.
 */
public enum AuxiliaryField {
    PLATFORM("platform"),
    LOGO("logo"),
    TAGS("tags"),
    NOTICE("notice"),
    DESCRIPTION("description"),
    URLS("urls"),
    ACTIVE("is_active"),
    STATUS("status"),
    DATE_ADDED("date_added"),
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