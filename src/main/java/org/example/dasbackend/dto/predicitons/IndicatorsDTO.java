package org.example.dasbackend.dto.predicitons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dasbackend.dto.predicitons.indicators.OscillatorsDTO;
import org.example.dasbackend.dto.predicitons.indicators.TrendsDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndicatorsDTO {

    @JsonProperty("final_signal")
    private String finalSignal;

    @JsonProperty("ma_signals")
    private List<String> maSignals;

    @JsonProperty("oscillator_signals")
    private List<String> oscillatorSignals;

    private OscillatorsDTO oscillators;

    private TrendsDTO trends;

    public static IndicatorsDTO empty() {
        return new IndicatorsDTO();
    }
}