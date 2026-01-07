package org.example.dasbackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.dasbackend.dto.filter.CryptoFilter;
import org.example.dasbackend.dto.helper.CryptocurrencyCreationHelper;
import org.example.dasbackend.model.crypto.Cryptocurrency;
import org.example.dasbackend.model.userroles.User;
import org.example.dasbackend.model.userroles.UserSavedCryptocurrency;
import org.example.dasbackend.repositories.CryptocurrencyRepository;
import org.example.dasbackend.repositories.UsersSavedCryptocurrenciesRepository;
import org.example.dasbackend.services.interfaces.CryptocurrencyService;
import org.example.dasbackend.services.interfaces.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptocurrencyServiceImpl implements CryptocurrencyService {
    private final CryptocurrencyRepository cryptocurrencyRepository;
    private final UsersSavedCryptocurrenciesRepository usersSavedCryptocurrenciesRepository;
    private final UserService userService;

    @Override
    public List<Cryptocurrency> getAllCryptocurrencies() {
        return cryptocurrencyRepository.findAll();
    }

    @Override
    public Page<Cryptocurrency> getCryptoPaged(Pageable pageable, CryptoFilter filter) {
        return cryptocurrencyRepository.getCryptocurrenciesPaginated(pageable, filter);
    }

    @Override
    public List<Cryptocurrency> getTop10ByPrice() {
        return cryptocurrencyRepository.getTop10ByPrice();
    }

    @Override
    public Cryptocurrency findTopByPriceDesc() {
        return cryptocurrencyRepository.findTopByOrderByPriceDesc();
    }

    @Override
    public Cryptocurrency findById(Long id) {
        return cryptocurrencyRepository.findById(id);
    }

    @Override
    public Cryptocurrency findByTicker(String ticker) {
        ticker = ticker.toUpperCase();
        return cryptocurrencyRepository.findByTicker(ticker);
    }

    @Override
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

    @Override
    public List<Cryptocurrency> getSaved(User currentUser) {
        List<UserSavedCryptocurrency> saved = usersSavedCryptocurrenciesRepository
                .findByUserId(Long.valueOf(currentUser.getId()));

        List<Cryptocurrency> cryptocurrencies = new ArrayList<>();
        for  (UserSavedCryptocurrency savedCryptocurrency : saved) {
            cryptocurrencies.add(findById(savedCryptocurrency.getCryptocurrencyId()));
        }

        return cryptocurrencies;
    }

    @Override
    public void removeCryptoFromUser(Long cryptoId, User currentUser) {
        UserSavedCryptocurrency saved = usersSavedCryptocurrenciesRepository
                .findByUserIdAndCryptocurrencyId(Long.valueOf(currentUser.getId()), cryptoId);
        usersSavedCryptocurrenciesRepository.delete(saved);
    }

    @Override
    public Cryptocurrency createCryptocurrency(CryptocurrencyCreationHelper helper) {
        Cryptocurrency cryptocurrency = new Cryptocurrency();
        return save(helper, cryptocurrency);
    }

    @Override
    public Cryptocurrency updateCryptocurrency(CryptocurrencyCreationHelper helper, Long id) {
        Cryptocurrency cryptocurrency = findById(id);
        if (cryptocurrency == null) {
            throw new IllegalArgumentException("Cryptocurrency with the given ID does not exist");
        }
        return save(helper, cryptocurrency);
    }

    @Override
    public Cryptocurrency save(CryptocurrencyCreationHelper helper, Cryptocurrency cryptocurrency) {
        if (helper.getName() != null && !helper.getName().isEmpty()) {
            cryptocurrency.setName(helper.getName());
        }
        cryptocurrency.setTicker(helper.getTicker());
        cryptocurrency.setPrice(helper.getPrice());
        return cryptocurrencyRepository.save(cryptocurrency);
    }
}
