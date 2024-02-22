package io.algostrategy.client.coinmarketcap.exception;

import io.algostrategy.client.coinmarketcap.domain.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.StandardException;

/**
 * An exception which can occur while invoking methods of the API.
 */
@AllArgsConstructor
@Getter
@StandardException
public class CoinmarketcapApiException extends RuntimeException {

    private ResponseStatus error;

    @Override
    public String getMessage() {
        if (error != null) {
            return error.getMsg();
        }
        return super.getMessage();
    }
}
