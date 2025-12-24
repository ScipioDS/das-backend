package org.example.dasbackend.dto.predicitons.onchain_signals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TvlDTO {
    private String signal;

    private String tvl;

    private String unit;
}
