package org.example.dasbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dasbackend.model.crypto.Cryptocurrency;
import org.example.dasbackend.services.CryptocurrencyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/{page}/{size}")
    public Page<Cryptocurrency> getCryptocurrenciesPaginated(@PathVariable int page, @PathVariable int size) {
        return cryptocurrencyService.getCryptoPaged(PageRequest.of(page,size));
    }
}
