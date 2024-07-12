package tn.course.teterin.integration_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.course.teterin.integration_service.dto.CurrencyConversionResponse;
import tn.course.teterin.integration_service.dto.CurrencyListResponse;
import tn.course.teterin.integration_service.service.CurrencyConversionService;
import tn.course.teterin.integration_service.service.CurrencyListService;

@RestController
public class IntegrationController {

    private final CurrencyConversionService currencyConversionService;
    private final CurrencyListService currencyListService;

    @Autowired
    public IntegrationController(CurrencyConversionService currencyConversionService, CurrencyListService currencyListService) {
        this.currencyConversionService = currencyConversionService;
        this.currencyListService = currencyListService;
    }

    @GetMapping("/convert")
    public CurrencyConversionResponse convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {
        return currencyConversionService.convertCurrency(from, to, amount);
    }

    @GetMapping("/list")
    public CurrencyListResponse getCurrencies() {
        return currencyListService.getCurrencies();
    }

}
