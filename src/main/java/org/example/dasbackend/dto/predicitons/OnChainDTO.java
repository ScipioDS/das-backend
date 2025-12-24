package org.example.dasbackend.dto.predicitons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dasbackend.dto.predicitons.onchain_signals.OnChainMetricsDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnChainDTO {

    @JsonProperty("final_signal")
    private String finalSignal;

    private OnChainMetricsDTO metrics;

    public static OnChainDTO empty() {
        return new OnChainDTO();
    }
}
