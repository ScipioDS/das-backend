package org.example.dasbackend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.dasbackend.dto.predicitons.*;
import org.example.dasbackend.services.interfaces.PredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/crypto")
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    /**
     * GET /crypto/lstm-analysis
     * Query params: ticker (default: BTC), lookback (default: 30), days (default: 7)
     *
     * Example: http://localhost:8080/crypto/lstm-analysis?ticker=ETH&lookback=30&days=7
     */
    @GetMapping("/lstm-analysis")
    public Mono<ResponseEntity<LSTAnalysisDTO>> getLstmAnalysis(
            @RequestParam(value = "ticker", defaultValue = "BTC") String ticker,
            @RequestParam(value = "lookback", defaultValue = "30") int lookback,
            @RequestParam(value = "days", defaultValue = "7") int predictionDays) {

        log.info("API Request: LSTM Analysis for {} with lookback={}, days={}",
                ticker, lookback, predictionDays);

        return predictionService.getLstmPrediction(ticker, lookback, predictionDays)
                .map(ResponseEntity::ok)
                .onErrorResume(error -> {
                    log.error("Error in lstm-analysis endpoint", error);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

    /**
     * GET /crypto/indicators
     * Query param: ticker (default: BTC)
     *
     * Example: http://localhost:8080/crypto/indicators?ticker=BTC
     */
    @GetMapping("/indicators")
    public Mono<ResponseEntity<IndicatorsDTO>> getIndicators(
            @RequestParam(value = "ticker", defaultValue = "BTC") String ticker) {

        log.info("API Request: Indicators for {}", ticker);

        return predictionService.getIndicators(ticker)
                .map(ResponseEntity::ok)
                .onErrorResume(error -> {
                    log.error("Error in indicators endpoint", error);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

    /**
     * GET /crypto/onchain
     * Query param: ticker (default: BTC)
     *
     * Example: http://localhost:8080/crypto/onchain?ticker=BTC
     */
    @GetMapping("/onchain")
    public Mono<ResponseEntity<OnChainDTO>> getOnchainMetrics(
            @RequestParam(value = "ticker", defaultValue = "BTC") String ticker) {

        log.info("API Request: On-chain metrics for {}", ticker);

        return predictionService.getOnchainMetrics(ticker)
                .map(ResponseEntity::ok)
                .onErrorResume(error -> {
                    log.error("Error in onchain endpoint", error);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

    /**
     * GET /crypto/sentiment
     * Query param: ticker (default: BTC)
     *
     * Example: http://localhost:8080/crypto/sentiment?ticker=BTC
     */
    @GetMapping("/sentiment")
    public Mono<ResponseEntity<SentimentDTO>> getSentimentAnalysis(
            @RequestParam(value = "ticker", defaultValue = "BTC") String ticker) {

        log.info("API Request: Sentiment analysis for {}", ticker);

        return predictionService.getSentimentAnalysis(ticker)
                .map(ResponseEntity::ok)
                .onErrorResume(error -> {
                    log.error("Error in sentiment endpoint", error);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

    /**
     * GET /crypto/analysis/complete
     * Composite endpoint - gets all analysis data in one call
     * Query param: ticker (default: BTC)
     *
     * Example: http://localhost:8080/crypto/analysis/complete?ticker=BTC
     */
    @GetMapping("/analysis/complete")
    public Mono<ResponseEntity<CryptoAnalysisCompositeDTO>> getCompleteAnalysis(
            @RequestParam(value = "ticker", defaultValue = "BTC") String ticker) {

        log.info("API Request: Complete analysis for {}", ticker);

        return Mono.zipDelayError(
                        predictionService.getIndicators(ticker)
                                .onErrorReturn(IndicatorsDTO.empty()),
                        predictionService.getOnchainMetrics(ticker)
                                .onErrorReturn(OnChainDTO.empty()),
                        predictionService.getSentimentAnalysis(ticker)
                                .onErrorReturn(SentimentDTO.empty()),
                        predictionService.getLstmPrediction(ticker, 30, 7)
                                .onErrorReturn(LSTAnalysisDTO.empty())
                )
                .map(tuple -> CryptoAnalysisCompositeDTO.builder()
                        .ticker(ticker)
                        .indicators(tuple.getT1())
                        .onchain(tuple.getT2())
                        .sentiment(tuple.getT3())
                        .lstmAnalysis(tuple.getT4())
                        .build()
                )
                .map(ResponseEntity::ok);
    }
}
