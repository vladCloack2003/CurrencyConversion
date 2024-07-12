package tn.course.teterin.integration_service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.course.teterin.integration_service.dto.CurrencyConversionResponse;
import tn.course.teterin.integration_service.config.FeignConfig;

@FeignClient(name = "currency-conversion-client", url = "https://api.getgeoapi.com/v2/currency", configuration = FeignConfig.class)
public interface CurrencyConversionClient {


    @GetMapping("/convert")
    CurrencyConversionResponse convertCurrency(
            @RequestParam("api_key") String apiKey,
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") double amount,
            @RequestParam("format") String format
    );
}
