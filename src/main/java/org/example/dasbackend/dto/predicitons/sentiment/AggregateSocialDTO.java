package org.example.dasbackend.dto.predicitons.sentiment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregateSocialDTO {

    @JsonProperty("combined_score")
    private Double combinedScore;

    @JsonProperty("data_points")
    private Integer dataPoints;

    @JsonProperty("overall_sentiment")
    private String overallSentiment;

    @JsonProperty("textblob_avg")
    private Double textblobAvg;

    @JsonProperty("vader_avg")
    private Double vaderAvg;
}
