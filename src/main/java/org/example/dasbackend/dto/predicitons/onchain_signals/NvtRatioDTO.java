package org.example.dasbackend.dto.predicitons.onchain_signals;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NvtRatioDTO {

    @JsonProperty("nvt_ratio")
    private Double nvtRatio;

    private String signal;
}