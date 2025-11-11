package org.example.dasbackend.repositories;

import org.example.dasbackend.model.crypto.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Integer> {
}
