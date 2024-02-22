package io.algostrategy.client.coinmarketcap.exception;

import io.algostrategy.client.coinmarketcap.domain.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.StandardException;

/**
 * An exception which can occur while invoking methods of the web API.
 */
@AllArgsConstructor
@Getter
@StandardException
public class CoinmarketcapWebApiException extends RuntimeException {

    private ResponseStatus error;

    @Override
    public String getMessage() {
        if (error != null) {
            return error.getMsg();
        }
        return super.getMessage();
    }
}
