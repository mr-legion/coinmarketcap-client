package io.algostrategy.client.coinmarketcap.exception;

/**
 * The exception is thrown when the parsing of the web page fails.
 */
public class CoinmarketcapWebParsingException extends RuntimeException {

    private static final long serialVersionUID = 8739440055771412588L;

    public CoinmarketcapWebParsingException(String message) {
        super(message);
    }

    public CoinmarketcapWebParsingException(Throwable cause) {
        super(cause);
    }

    public CoinmarketcapWebParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}