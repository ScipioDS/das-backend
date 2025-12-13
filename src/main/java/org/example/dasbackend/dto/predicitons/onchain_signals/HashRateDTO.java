package org.example.dasbackend.dto.predicitons.onchain_signals;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HashRateDTO {

    @JsonProperty("hash_rate")
    private String hashRate;

    private String signal;

    private String unit;
}