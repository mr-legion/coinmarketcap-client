package io.algostrategy.client.coinmarketcap.domain.cryptocurrency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Various resource URLs for cryptocurrency.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Urls {

    private List<String> website;

    @JsonProperty("technical_doc")
    private List<String> technicalDoc;

    private List<String> explorer;

    @JsonProperty("source_code")
    private List<String> sourceCode;

    @JsonProperty("message_board")
    private List<String> messageBoard;

    private List<String> announcement;

    private List<String> chat;
    private List<String> reddit;
    private List<String> twitter;
}
