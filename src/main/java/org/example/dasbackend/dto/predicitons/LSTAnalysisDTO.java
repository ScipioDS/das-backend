package org.example.dasbackend.dto.predicitons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dasbackend.dto.predicitons.lst.ModelMetricsDTO;
import org.example.dasbackend.dto.predicitons.lst.PredictionDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LSTAnalysisDTO {

    @JsonProperty("current_price")
    private Double currentPrice;

    @JsonProperty("model_metrics")
    private ModelMetricsDTO modelMetrics;

    private List<PredictionDTO> predictions;

    @JsonProperty("price_change")
    private Double priceChange;

    @JsonProperty("price_change_percent")
    private Double priceChangePercent;

    private String trend;

    public static LSTAnalysisDTO empty() {
        return new LSTAnalysisDTO();
    }
}
