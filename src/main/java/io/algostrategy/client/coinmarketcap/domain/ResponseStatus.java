package io.algostrategy.client.coinmarketcap.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static io.algostrategy.client.coinmarketcap.constant.CoinmarketcapApiConstants.DATE_FORMAT;

/**
 * Metadata of response.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseStatus {

    @JsonProperty("error_code")
    private Integer code;

    @JsonProperty("error_message")
    private String msg;

    @JsonProperty("credit_count")
    private Integer creditCount;

    private Integer elapsed;

    @JsonFormat(pattern = DATE_FORMAT)
    @JsonProperty("timestamp")
    private Date date;
}
