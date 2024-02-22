package io.algostrategy.client.coinmarketcap.exception;

import lombok.AllArgsConstructor;
import lombok.experimental.StandardException;

/**
 * The exception is thrown when the parsing of the web page fails.
 */
@AllArgsConstructor
@StandardException
public class CoinmarketcapWebParsingException extends RuntimeException {
}