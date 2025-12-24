package org.example.dasbackend.dto.predicitons.onchain_signals;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeFlowsDTO {
    private Long inflow;

    @JsonProperty("net_flow")
    private Long netFlow;

    private Long outflow;

    private String signal;
}