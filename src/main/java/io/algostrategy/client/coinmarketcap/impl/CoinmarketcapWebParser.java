package io.algostrategy.client.coinmarketcap.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.algostrategy.client.coinmarketcap.domain.web.DEXPage;
import io.algostrategy.client.coinmarketcap.exception.CoinmarketcapWebParsingException;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static io.algostrategy.client.coinmarketcap.constant.CoinmarketcapApiConstants.WEB_BASE_URL;

/**
 * This class is used to parse Coinmarketcap web pages.
 */
@AllArgsConstructor
public class CoinmarketcapWebParser {

    private static final String DEX_PATH = "/rankings/exchanges/dex";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final OkHttpClient okHttpClient;

    public DEXPage parseDEXPage() {

        Request request = new Request.Builder().url(WEB_BASE_URL + DEX_PATH).build();

        try (Response response = okHttpClient.newCall(request).execute()) {

            String page = response.body().string();

            int startIndex = page.indexOf("{", page.indexOf("\"pageProps\""));

            StringBuilder pagePropsStr = new StringBuilder();

            for (int openBracketCounter = 0; ; startIndex++) {

                char currChar = page.charAt(startIndex);
                pagePropsStr.append(currChar);

                boolean isOpenBracket = currChar == '{';
                boolean isCloseBracket = currChar == '}';

                if (isOpenBracket) {
                    openBracketCounter++;
                } else if (isCloseBracket) {
                    openBracketCounter--;
                }

                if (openBracketCounter == 0) {
                    break;
                }
            }

            return objectMapper.readValue(pagePropsStr.toString(), DEXPage.class);
        } catch (Exception e) {
            throw new CoinmarketcapWebParsingException("failed to parse DEX page", e);
        }
    }
}
