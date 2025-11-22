package org.example.dasbackend.repositories;

import org.example.dasbackend.model.userroles.UserSavedCryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersSavedCryptocurrenciesRepository extends JpaRepository<UserSavedCryptocurrency, Integer> {
    public List<UserSavedCryptocurrency> findByUserId(Long id);

    public UserSavedCryptocurrency findByUserIdAndCryptocurrencyId(Long userId, Long cryptoId);
}
