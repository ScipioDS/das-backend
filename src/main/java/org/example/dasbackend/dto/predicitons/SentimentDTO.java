package org.example.dasbackend.dto.predicitons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dasbackend.dto.predicitons.sentiment.NewsSentimentDTO;
import org.example.dasbackend.dto.predicitons.sentiment.SocialSentimentDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SentimentDTO {

    @JsonProperty("final_signal")
    private String finalSignal;

    @JsonProperty("news_sentiment")
    private NewsSentimentDTO newsSentiment;

    @JsonProperty("social_sentiment")
    private SocialSentimentDTO socialSentiment;

    public static SentimentDTO empty() {
        return new SentimentDTO();
    }
}
