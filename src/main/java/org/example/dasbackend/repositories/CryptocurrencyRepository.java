package org.example.dasbackend.repositories;

import org.example.dasbackend.dto.filter.CryptoFilter;
import org.example.dasbackend.model.crypto.Cryptocurrency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Integer> {
    @Query("SELECT c FROM Cryptocurrency c " +
            "WHERE (:#{#filter.name} IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :#{#filter.name}, '%'))) " +
            "OR (:#{#filter.ticker} IS NULL OR LOWER(c.ticker) LIKE LOWER(CONCAT('%', :#{#filter.ticker}, '%')))")
    public Page<Cryptocurrency> getCryptocurrenciesPaginated(Pageable pageable, CryptoFilter filter);

    @Query("SELECT c FROM Cryptocurrency c ORDER BY c.price DESC LIMIT 10")
    public List<Cryptocurrency> getTop10ByPrice();

    public Cryptocurrency findTopByOrderByPriceDesc();
}
