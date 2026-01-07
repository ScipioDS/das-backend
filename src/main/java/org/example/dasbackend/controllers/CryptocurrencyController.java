package org.example.dasbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dasbackend.dto.filter.CryptoFilter;
import org.example.dasbackend.dto.helper.CryptocurrencyCreationHelper;
import org.example.dasbackend.model.crypto.Cryptocurrency;
import org.example.dasbackend.model.userroles.User;
import org.example.dasbackend.services.interfaces.CryptocurrencyService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("permitAll()")
@RestController
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CryptocurrencyController {
    private final CryptocurrencyService cryptocurrencyService;

    @GetMapping("/all")
    public List<Cryptocurrency> getAllCryptocurrencies() {
        return cryptocurrencyService.getAllCryptocurrencies();
    }

    @PostMapping("/{page}/{size}")
    public Page<Cryptocurrency> getCryptocurrenciesPaginated(@PathVariable int page, @PathVariable int size, @RequestBody CryptoFilter filter) {
        return cryptocurrencyService.getCryptoPaged(PageRequest.of(page,size), filter);
    }

    @GetMapping("/top10")
    public List<Cryptocurrency> getTop10CryptocurrenciesByPrice() {
        return cryptocurrencyService.getTop10ByPrice();
    }

    @GetMapping("/top")
    public Cryptocurrency getTopCryptocurrenciesByPrice() {
        return cryptocurrencyService.findTopByPriceDesc();
    }

    @GetMapping("/{ticker}")
    public Cryptocurrency getCryptocurrenciesByTicker(@PathVariable String ticker) {
        return cryptocurrencyService.findByTicker(ticker);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/save/{cryptoId}")
    public Cryptocurrency saveCryptocurrency(@PathVariable Long cryptoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        return cryptocurrencyService.saveCryptocurrencyToUser(cryptoId, currentUser);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/unsave/{cryptoId}")
    public void removeSavedCryptocurrency(@PathVariable Long cryptoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        cryptocurrencyService.removeCryptoFromUser(cryptoId, currentUser);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/saved")
    public List<Cryptocurrency> getSavedCryptocurrency() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        return cryptocurrencyService.getSaved(currentUser);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public Cryptocurrency createCryptocurrency(@RequestBody CryptocurrencyCreationHelper helper) {
        return cryptocurrencyService.createCryptocurrency(helper);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{cryptoId}")
    public Cryptocurrency updateCryptocurrency(@RequestBody CryptocurrencyCreationHelper helper,
                                               @PathVariable Long cryptoId) {
        return cryptocurrencyService.updateCryptocurrency(helper, cryptoId);
    }
}
