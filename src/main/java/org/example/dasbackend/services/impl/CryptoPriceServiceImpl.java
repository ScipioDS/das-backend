package org.example.dasbackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.dasbackend.model.crypto.CryptoPrices;
import org.example.dasbackend.repositories.CryptoPriceRepository;
import org.example.dasbackend.services.interfaces.CryptoPriceService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CryptoPriceServiceImpl implements CryptoPriceService {
    private final CryptoPriceRepository cryptoPriceRepository;

    @Override
    public List<CryptoPrices> findAllByTicker(String ticker) {
        ticker = ticker.toUpperCase();
        return cryptoPriceRepository.findAllByTicker(ticker);
    }

    // Get last N months for a specific ticker
    @Override
    public List<CryptoPrices> getLastNMonthsData(String ticker, int months) {
        OffsetDateTime startDate = OffsetDateTime.now().minusMonths(months);
        ticker = ticker.toUpperCase();
        return cryptoPriceRepository.findByTickerAndLastNMonths(ticker, startDate);
    }

    // Get last N months for all tickers
    @Override
    public List<CryptoPrices> getAllLastNMonthsData(int months) {
        OffsetDateTime startDate = OffsetDateTime.now().minusMonths(months);
        return cryptoPriceRepository.findAllByLastNMonths(startDate);
    }
}
