package org.example.dasbackend.services.interfaces;

import org.example.dasbackend.dto.filter.CryptoFilter;
import org.example.dasbackend.dto.helper.CryptocurrencyCreationHelper;
import org.example.dasbackend.model.crypto.Cryptocurrency;
import org.example.dasbackend.model.userroles.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CryptocurrencyService {
    List<Cryptocurrency> getAllCryptocurrencies();

    Page<Cryptocurrency> getCryptoPaged(Pageable pageable, CryptoFilter filter);

    List<Cryptocurrency> getTop10ByPrice();

    Cryptocurrency findTopByPriceDesc();

    Cryptocurrency findById(Long id);

    Cryptocurrency findByTicker(String ticker);

    Cryptocurrency saveCryptocurrencyToUser(Long cryptocurrencyId, User user);

    List<Cryptocurrency> getSaved(User currentUser);

    void removeCryptoFromUser(Long cryptoId, User currentUser);

    Cryptocurrency createCryptocurrency(CryptocurrencyCreationHelper helper);

    Cryptocurrency updateCryptocurrency(CryptocurrencyCreationHelper helper, Long id);

    Cryptocurrency save(CryptocurrencyCreationHelper helper, Cryptocurrency cryptocurrency);
}
