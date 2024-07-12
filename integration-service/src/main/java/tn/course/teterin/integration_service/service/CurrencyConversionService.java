package tn.course.teterin.integration_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.course.teterin.integration_service.dto.CurrencyConversionResponse;
import tn.course.teterin.integration_service.entity.CurrencyConversionEntity;
import tn.course.teterin.integration_service.exception.*;
import tn.course.teterin.integration_service.feignclient.CurrencyConversionClient;
import tn.course.teterin.integration_service.repository.CurrencyConversionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConversionService {

    @Value("${currency.conversion.api.access_key}")
    private String apiKey;

    private final CurrencyConversionClient currencyConversionClient;
    private final CurrencyConversionRepository currencyConversionRepository;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionService.class);

    public CurrencyConversionService(CurrencyConversionClient currencyConversionClient, CurrencyConversionRepository currencyConversionRepository) {
        this.currencyConversionClient = currencyConversionClient;
        this.currencyConversionRepository = currencyConversionRepository;
    }

    public CurrencyConversionResponse convertCurrency(String from, String to, double amount) {
        logger.info("Converting currency: from={}, to={}, amount={}", from, to, amount);

        CurrencyConversionResponse response;
        try {
            response = currencyConversionClient.convertCurrency(apiKey, from, to, amount, "json");
            logger.info("API response: {}", response);
        } catch (Exception e) {
            logger.error("Error calling currency conversion API", e);
            throw new RuntimeException("Error calling currency conversion API", e);
        }

        // Log detailed response fields
        logger.info("Response Status: {}", response.getStatus());
        logger.info("Updated Date: {}", response.getUpdatedDate());
        logger.info("Base Currency Code: {}", response.getBaseCurrencyCode());
        logger.info("Base Currency Name: {}", response.getBaseCurrencyName());
        logger.info("Amount: {}", response.getAmount());

        if (response.getRates() != null) {
            CurrencyConversionResponse.CurrencyRate convertedCurrency = response.getRates().get(to);
            if (convertedCurrency != null) {
                Map<String, CurrencyConversionResponse.CurrencyRate> rates = new HashMap<>();
                rates.put(to, convertedCurrency);
                response.setRates(rates);

                logger.info("Converted Currency Name: {}", convertedCurrency.getCurrencyName());
                logger.info("Converted Currency Rate: {}", convertedCurrency.getRate());
                logger.info("Converted Currency Rate for Amount: {}", convertedCurrency.getRateForAmount());

                CurrencyConversionEntity entity = new CurrencyConversionEntity();
                entity.setFromCurrency(from);
                entity.setToCurrency(to);
                entity.setAmount(amount);
                entity.setResult(Double.parseDouble(convertedCurrency.getRateForAmount()));
                entity.setTimestamp(System.currentTimeMillis());

                currencyConversionRepository.save(entity);
            } else {
                logger.warn("Rates for the converted currency not available");
            }
        } else {
            logger.warn("Rates not available");
        }

        if (!"success".equalsIgnoreCase(response.getStatus())) {
            handleApiError(response);
        }

        return response;
    }

    private void handleApiError(CurrencyConversionResponse response) {
        String status = response.getStatus();
        logger.error("API returned error with status: {}", status);

        switch (status) {
            case "400":
                throw new BadRequestException("Bad Request");
            case "403":
                throw new ForbiddenException("Forbidden: Invalid API key or usage limit reached");
            case "404":
                throw new NotFoundException("Not Found: Resource not found or invalid format requested");
            case "405":
                throw new MethodNotAllowedException("Method Not Allowed");
            case "500":
                throw new InternalServerErrorException("Internal Server Error");
            default:
                throw new RuntimeException("Unknown error: " + status);
        }
    }
}
