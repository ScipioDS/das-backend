package org.example.dasbackend.dto.predicitons.onchain_signals;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnChainMetricsDTO {

    @JsonProperty("active_addresses")
    private Object activeAddresses;  // Can be null or a number

    @JsonProperty("exchange_flows")
    private ExchangeFlowsDTO exchangeFlows;

    @JsonProperty("hash_rate")
    private HashRateDTO hashRate;

    @JsonProperty("mvrv_ratio")
    private MvrvRatioDTO mvrvRatio;

    @JsonProperty("nvt_ratio")
    private NvtRatioDTO nvtRatio;

    @JsonProperty("transaction_count")
    private Object transactionCount;  // Can be null or a number

    private TvlDTO tvl;

    @JsonProperty("whale_movements")
    private WhaleMovementsDTO whaleMovements;
}