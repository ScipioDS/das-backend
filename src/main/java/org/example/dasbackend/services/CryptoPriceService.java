package org.example.dasbackend.services;

import lombok.RequiredArgsConstructor;
import org.example.dasbackend.model.crypto.CryptoPrices;
import org.example.dasbackend.repositories.CryptoPriceRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CryptoPriceService {
    private final CryptoPriceRepository cryptoPriceRepository;

    public List<CryptoPrices> findAllByTicker(String ticker) {
        ticker = ticker.toUpperCase();
        return cryptoPriceRepository.findAllByTicker(ticker);
    }

    // Get last N months for a specific ticker
    public List<CryptoPrices> getLastNMonthsData(String ticker, int months) {
        OffsetDateTime startDate = OffsetDateTime.now().minusMonths(months);
        ticker = ticker.toUpperCase();
        return cryptoPriceRepository.findByTickerAndLastNMonths(ticker, startDate);
    }

    // Get last N months for all tickers
    public List<CryptoPrices> getAllLastNMonthsData(int months) {
        OffsetDateTime startDate = OffsetDateTime.now().minusMonths(months);
        return cryptoPriceRepository.findAllByLastNMonths(startDate);
    }
}
