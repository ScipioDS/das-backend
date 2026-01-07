package org.example.dasbackend.services.interfaces;

import org.example.dasbackend.dto.predicitons.IndicatorsDTO;
import org.example.dasbackend.dto.predicitons.LSTAnalysisDTO;
import org.example.dasbackend.dto.predicitons.OnChainDTO;
import org.example.dasbackend.dto.predicitons.SentimentDTO;
import reactor.core.publisher.Mono;

public interface PredictionService {
    Mono<LSTAnalysisDTO> getLstmPrediction(String ticker, int lookback, int predictionDays);

    Mono<IndicatorsDTO> getIndicators(String ticker);

    Mono<OnChainDTO> getOnchainMetrics(String ticker);

    Mono<SentimentDTO> getSentimentAnalysis(String ticker);
}
