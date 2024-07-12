package tn.course.teterin.router_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.course.teterin.router_service.dto.CurrencyConversionResponse;
import tn.course.teterin.router_service.dto.CurrencyListResponse;
import tn.course.teterin.router_service.service.CurrencyListService;
import tn.course.teterin.router_service.service.CurrencyConvService;

@RestController
public class RouterController {

    private final CurrencyConvService routerService;

    private final CurrencyListService currencyListService;


    @Autowired
    public RouterController(CurrencyConvService routerService, CurrencyListService currencyListService) {
        this.routerService = routerService;
        this.currencyListService = currencyListService;
    }

    @GetMapping("/convert")
    public CurrencyConversionResponse convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {
        return routerService.convertCurrency(from, to, amount);
    }

    @GetMapping("/list")
    public CurrencyListResponse getCurrencies() {
        return currencyListService.getCurrencies();
    }

}
