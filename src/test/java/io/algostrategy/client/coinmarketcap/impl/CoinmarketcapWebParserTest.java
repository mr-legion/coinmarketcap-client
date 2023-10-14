package io.algostrategy.client.coinmarketcap.impl;

import io.algostrategy.client.coinmarketcap.CoinmarketcapClientFactory;
import io.algostrategy.client.coinmarketcap.domain.web.DEXPage;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class CoinmarketcapWebParserTest {

    private final CoinmarketcapWebParser coinmarketcapWebParser = CoinmarketcapClientFactory.newWebParser();

    @Test
    public void parseDEXPage_ShouldReturnDEXPage() {
        DEXPage dexPage = coinmarketcapWebParser.parseDEXPage();
        assertThat(dexPage, notNullValue());
    }
}