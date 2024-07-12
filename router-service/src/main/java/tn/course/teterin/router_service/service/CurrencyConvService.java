package tn.course.teterin.router_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.course.teterin.router_service.dto.CurrencyConversionResponse;
import tn.course.teterin.router_service.feignclient.CurrencyConversionClient;

@Service
public class CurrencyConvService {

    private final CurrencyConversionClient convertCurrencyClient;

    @Autowired
    public CurrencyConvService(CurrencyConversionClient convertCurrencyClient) {
        this.convertCurrencyClient = convertCurrencyClient;
    }

    public CurrencyConversionResponse convertCurrency(String from, String to, double amount) {
        return convertCurrencyClient.convertCurrency(from, to, amount);
    }
}
