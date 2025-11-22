package org.example.dasbackend.services;

import lombok.RequiredArgsConstructor;
import org.example.dasbackend.dto.filter.CryptoFilter;
import org.example.dasbackend.model.crypto.Cryptocurrency;
import org.example.dasbackend.model.userroles.User;
import org.example.dasbackend.model.userroles.UserSavedCryptocurrency;
import org.example.dasbackend.repositories.CryptocurrencyRepository;
import org.example.dasbackend.repositories.UsersSavedCryptocurrenciesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptocurrencyService {
    private final CryptocurrencyRepository cryptocurrencyRepository;
    private final UsersSavedCryptocurrenciesRepository usersSavedCryptocurrenciesRepository;
    private final UserService userService;

    public List<Cryptocurrency> getAllCryptocurrencies() {
        return cryptocurrencyRepository.findAll();
    }

    public Page<Cryptocurrency> getCryptoPaged(Pageable pageable, CryptoFilter filter) {
        return cryptocurrencyRepository.getCryptocurrenciesPaginated(pageable, filter);
    }

    public List<Cryptocurrency> getTop10ByPrice() {
        return cryptocurrencyRepository.getTop10ByPrice();
    }

    public Cryptocurrency findTopByPriceDesc() {
        return cryptocurrencyRepository.findTopByOrderByPriceDesc();
    }

    public Cryptocurrency findById(Long id) {
        return cryptocurrencyRepository.findById(id);
    }

    public Cryptocurrency findByTicker(String ticker) {
        ticker = ticker.toUpperCase();
        return cryptocurrencyRepository.findByTicker(ticker);
    }

    public Cryptocurrency saveCryptocurrencyToUser(Long cryptocurrencyId, User user) {
        Cryptocurrency cryptocurrency = cryptocurrencyRepository.findById(cryptocurrencyId);
        if (cryptocurrency == null || user == null) {
            throw new IllegalArgumentException("Invalid cryptocurrency ID or user ID");
        }

        UserSavedCryptocurrency userSavedCryptocurrency = new UserSavedCryptocurrency();
        userSavedCryptocurrency.setUserId(user.getId());
        userSavedCryptocurrency.setCryptocurrencyId(cryptocurrencyId);

        usersSavedCryptocurrenciesRepository.save(userSavedCryptocurrency);
        return cryptocurrency;
    }

    public List<Cryptocurrency> getSaved(User currentUser) {
        List<UserSavedCryptocurrency> saved = usersSavedCryptocurrenciesRepository
                .findByUserId(Long.valueOf(currentUser.getId()));

        List<Cryptocurrency> cryptocurrencies = new ArrayList<>();
        for  (UserSavedCryptocurrency savedCryptocurrency : saved) {
            cryptocurrencies.add(findById(savedCryptocurrency.getCryptocurrencyId()));
        }

        return cryptocurrencies;
    }

    public void removeCryptoFromUser(Long cryptoId, User currentUser) {
        UserSavedCryptocurrency saved = usersSavedCryptocurrenciesRepository
                .findByUserIdAndCryptocurrencyId(Long.valueOf(currentUser.getId()), cryptoId);
        usersSavedCryptocurrenciesRepository.delete(saved);
    }
}
