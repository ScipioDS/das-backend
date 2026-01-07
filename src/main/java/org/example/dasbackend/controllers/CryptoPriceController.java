package org.example.dasbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dasbackend.model.crypto.CryptoPrices;
import org.example.dasbackend.services.interfaces.CryptoPriceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crypto-price")
@RequiredArgsConstructor
public class CryptoPriceController {
    private final CryptoPriceService cryptoPriceService;

    @GetMapping("{ticker}")
    public List<CryptoPrices> findAllByTicker(@PathVariable String ticker) {
        return this.cryptoPriceService.findAllByTicker(ticker);
    }

    @GetMapping("{ticker}/last-months/{months}")
    public List<CryptoPrices> getLastNMonthsData(@PathVariable String ticker, @PathVariable int months) {
        return this.cryptoPriceService.getLastNMonthsData(ticker, months);
    }
}
