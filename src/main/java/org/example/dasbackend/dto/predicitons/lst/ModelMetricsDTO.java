package org.example.dasbackend.dto.predicitons.lst;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelMetricsDTO {

    private Double mape;

    @JsonProperty("r2_score")
    private Double r2Score;

    private Double rmse;

    @JsonProperty("test_samples")
    private Integer testSamples;

    @JsonProperty("training_samples")
    private Integer trainingSamples;
}
