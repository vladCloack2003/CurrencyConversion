package tn.course.teterin.router_service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.course.teterin.router_service.dto.CurrencyConversionResponse;

@FeignClient(name = "currency-conversion-client", url = "http://localhost:8081")
public interface CurrencyConversionClient {

    @GetMapping("/convert")
    CurrencyConversionResponse convertCurrency(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") double amount
    );
}
