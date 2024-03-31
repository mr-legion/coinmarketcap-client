package io.algostrategy.client.coinmarketcap.exception;

import lombok.experimental.StandardException;

/**
 * An exception which can occur while invoking methods of the web API.
 */
@StandardException
public class CoinmarketcapWebApiException extends RuntimeException {
}
