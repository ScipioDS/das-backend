package org.example.dasbackend.repositories;

import org.example.dasbackend.model.crypto.CryptoPrices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface CryptoPriceRepository extends JpaRepository<CryptoPrices,Long> {
    List<CryptoPrices> findAllByTicker(String ticker);

    @Query("SELECT cp FROM CryptoPrices cp WHERE cp.ticker = :ticker AND cp.date >= :startDate ORDER BY cp.date DESC")
    List<CryptoPrices> findByTickerAndLastNMonths(String ticker, OffsetDateTime startDate);

    // Alternative: Get last N months for all tickers
    @Query("SELECT cp FROM CryptoPrices cp WHERE cp.date >= :startDate ORDER BY cp.ticker, cp.date DESC")
    List<CryptoPrices> findAllByLastNMonths(OffsetDateTime startDate);
}