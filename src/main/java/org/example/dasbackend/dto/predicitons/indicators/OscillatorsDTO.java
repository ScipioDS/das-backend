package org.example.dasbackend.dto.predicitons.indicators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OscillatorsDTO {

    private Double ADX;

    private Double CCI;

    private Double MACD;

    @com.fasterxml.jackson.annotation.JsonProperty("MACD_signal")
    private Double MACDSignal;

    private Double RSI;

    private Double Stochastic;
}