package org.example.dasbackend.dto.predicitons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CryptoAnalysisCompositeDTO {

    private String ticker;

    private IndicatorsDTO indicators;

    private OnChainDTO onchain;

    private SentimentDTO sentiment;

    private LSTAnalysisDTO lstmAnalysis;

    /**
     * Calculate overall signal based on all analysis sources
     */
    public String getOverallSignal() {
        int buyCount = 0;
        int sellCount = 0;

        // Count buy/sell signals from indicators
        if ("BUY".equalsIgnoreCase(indicators.getFinalSignal())) {
            buyCount++;
        } else if ("SELL".equalsIgnoreCase(indicators.getFinalSignal())) {
            sellCount++;
        }

        // Count buy/sell signals from on-chain
        if ("BUY".equalsIgnoreCase(onchain.getFinalSignal())) {
            buyCount++;
        } else if ("SELL".equalsIgnoreCase(onchain.getFinalSignal())) {
            sellCount++;
        }

        // Count buy/sell signals from sentiment
        if ("BUY".equalsIgnoreCase(sentiment.getFinalSignal())) {
            buyCount++;
        } else if ("SELL".equalsIgnoreCase(sentiment.getFinalSignal())) {
            sellCount++;
        }

        // Aggregate logic
        if (buyCount >= 2) {
            return "STRONG_BUY";
        } else if (buyCount == 1 && sellCount == 0) {
            return "BUY";
        } else if (sellCount >= 2) {
            return "STRONG_SELL";
        } else if (sellCount == 1 && buyCount == 0) {
            return "SELL";
        }

        return "NEUTRAL";
    }
}
