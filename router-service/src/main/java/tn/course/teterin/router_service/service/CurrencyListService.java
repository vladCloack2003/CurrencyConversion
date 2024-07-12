package tn.course.teterin.router_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.course.teterin.router_service.dto.CurrencyListResponse;
import tn.course.teterin.router_service.feignclient.CurrencyListClient;

@Service
public class CurrencyListService {

    private final CurrencyListClient currencyListClient;

    @Autowired
    public CurrencyListService(CurrencyListClient currencyListClient) {
        this.currencyListClient = currencyListClient;
    }

    public CurrencyListResponse getCurrencies() {
        return currencyListClient.getCurrencies();
    }
}
