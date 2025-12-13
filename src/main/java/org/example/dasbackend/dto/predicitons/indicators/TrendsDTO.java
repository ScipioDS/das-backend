package org.example.dasbackend.dto.predicitons.indicators;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrendsDTO {

    @JsonProperty("Bollinger_Position")
    private Double bollingerPosition;

    private Double EMA;

    private Double SMA;

    private Double WMA;
}
