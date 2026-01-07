package org.example.dasbackend.services.interfaces;

import org.example.dasbackend.model.crypto.CryptoPrices;

import java.util.List;

public interface CryptoPriceService {
    List<CryptoPrices> findAllByTicker(String ticker);

    // Get last N months for a specific ticker
    List<CryptoPrices> getLastNMonthsData(String ticker, int months);

    // Get last N months for all tickers
    List<CryptoPrices> getAllLastNMonthsData(int months);
}
