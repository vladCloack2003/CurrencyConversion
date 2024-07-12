package tn.course.teterin.integration_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.course.teterin.integration_service.dto.CurrencyListResponse;
import tn.course.teterin.integration_service.exception.*;
import tn.course.teterin.integration_service.feignclient.CurrencyListClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CurrencyListService {

    @Value("${currency.conversion.api.access_key}")
    private String apiKey;

    private final CurrencyListClient currencyListClient;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyListService.class);

    public CurrencyListService(CurrencyListClient currencyListClient) {
        this.currencyListClient = currencyListClient;
    }

    public CurrencyListResponse getCurrencies() {
        logger.info("Fetching currency list with API key: {}", apiKey);

        CurrencyListResponse response;
        try {
            response = currencyListClient.getCurrencyList(apiKey);
            logger.info("API response: {}", response);
        } catch (Exception e) {
            logger.error("Error calling currency list API", e);
            throw new RuntimeException("Error calling currency list API", e);
        }

        // Log detailed response fields
        logger.info("Response Status: {}", response.getStatus());
        logger.info("Currencies:");
        if (response.getCurrencies() != null) {
            response.getCurrencies().forEach((code, name) -> {
                logger.info("{} - {}", code, name);
            });
        } else {
            logger.warn("No currencies returned");
        }

        if (!"success".equalsIgnoreCase(response.getStatus())) {
            handleApiError(response);
        }

        return response;
    }

    private void handleApiError(CurrencyListResponse response) {
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
