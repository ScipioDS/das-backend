package org.example.dasbackend.services;

import lombok.RequiredArgsConstructor;
import org.example.dasbackend.dto.filter.CryptoFilter;
import org.example.dasbackend.model.crypto.Cryptocurrency;
import org.example.dasbackend.repositories.CryptocurrencyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptocurrencyService {
    private final CryptocurrencyRepository cryptocurrencyRepository;

    public List<Cryptocurrency> getAllCryptocurrencies() {
        return cryptocurrencyRepository.findAll();
    }

    public Page<Cryptocurrency> getCryptoPaged(Pageable pageable, CryptoFilter filter) {
        return cryptocurrencyRepository.getCryptocurrenciesPaginated(pageable, filter);
    }
}
