package org.example.dasbackend.dto.predicitons.onchain_signals;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MvrvRatioDTO {

    @JsonProperty("mvrv_ratio")
    private Double mvrvRatio;

    private String signal;
}