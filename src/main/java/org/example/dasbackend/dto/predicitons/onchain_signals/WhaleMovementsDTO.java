package org.example.dasbackend.dto.predicitons.onchain_signals;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhaleMovementsDTO {

    @JsonProperty("avg_tx_size")
    private Double avgTxSize;

    private String signal;

    @JsonProperty("whale_tx_count")
    private Integer whaleTxCount;
}