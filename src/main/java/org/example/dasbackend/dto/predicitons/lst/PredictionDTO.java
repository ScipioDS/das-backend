package org.example.dasbackend.dto.predicitons.lst;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictionDTO {

    private String date;

    @JsonProperty("predicted_price")
    private Double predictedPrice;
}
