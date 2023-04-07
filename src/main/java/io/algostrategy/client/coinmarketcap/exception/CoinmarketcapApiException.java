package io.algostrategy.client.coinmarketcap.exception;

import io.algostrategy.client.coinmarketcap.domain.ResponseStatus;

/**
 * An exception which can occur while invoking methods of the API.
 */
public class CoinmarketcapApiException extends RuntimeException {

    private static final long serialVersionUID = -1176442850030484093L;

    private ResponseStatus status;

    public CoinmarketcapApiException(ResponseStatus status) {
        this.status = status;
    }

    public CoinmarketcapApiException() {
        super();
    }

    public CoinmarketcapApiException(String message) {
        super(message);
    }

    public CoinmarketcapApiException(Throwable cause) {
        super(cause);
    }

    public CoinmarketcapApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return the response status from API,
     */
    public ResponseStatus getResponseStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        if (status != null) {
            return status.getMsg();
        }
        return super.getMessage();
    }
}
