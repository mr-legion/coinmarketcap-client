package io.algostrategy.client.coinmarketcap.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Page wrapper.
 *
 * @param <T> payload type
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page<T> {

    private Boolean hasNextPage;

    private Integer total;
    private Integer count;

    @JsonProperty("pageList")
    private T data;
}
