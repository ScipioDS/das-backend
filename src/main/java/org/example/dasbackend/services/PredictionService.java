package org.example.dasbackend.services;

import lombok.extern.slf4j.Slf4j;
import org.example.dasbackend.dto.predicitons.IndicatorsDTO;
import org.example.dasbackend.dto.predicitons.LSTAnalysisDTO;
import org.example.dasbackend.dto.predicitons.OnChainDTO;
import org.example.dasbackend.dto.predicitons.SentimentDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PredictionService {
    private final WebClient flaskWebClient;

    public PredictionService(@Qualifier("flaskWebClient") WebClient flaskWebClient) {
        this.flaskWebClient = flaskWebClient;
    }

    public Mono<LSTAnalysisDTO> getLstmPrediction(String ticker, int lookback, int predictionDays) {
        return flaskWebClient
                .get()
                .uri("/lst_analysis?ticker={ticker}&lookback={lookback}&days={days}",
                        ticker.toUpperCase(), lookback, predictionDays)
                .retrieve()
                .bodyToMono(LSTAnalysisDTO.class)
                .doOnError(error -> log.error("Error fetching LSTM prediction", error));
    }

    public Mono<IndicatorsDTO> getIndicators(String ticker) {
        return flaskWebClient
                .get()
                .uri("/indicators?ticker={ticker}", ticker.toUpperCase())
                .retrieve()
                .bodyToMono(IndicatorsDTO.class)
                .doOnError(error -> log.error("Error fetching indicators", error));
    }

    public Mono<OnChainDTO> getOnchainMetrics(String ticker) {
        return flaskWebClient
                .get()
                .uri("/onchain?ticker={ticker}", ticker.toUpperCase())
                .retrieve()
                .bodyToMono(OnChainDTO.class)
                .doOnError(error -> log.error("Error fetching on-chain metrics", error));
    }

    public Mono<SentimentDTO> getSentimentAnalysis(String ticker) {
        return flaskWebClient
                .get()
                .uri("/sentiment?ticker={ticker}", ticker.toUpperCase())
                .retrieve()
                .bodyToMono(SentimentDTO.class)
                .doOnError(error -> log.error("Error fetching sentiment analysis", error));
    }
}
