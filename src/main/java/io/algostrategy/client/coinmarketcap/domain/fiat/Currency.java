package io.algostrategy.client.coinmarketcap.domain.fiat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Currency data.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

    private Integer id;

    private String name;
    private String symbol;
    private String sign;
}
