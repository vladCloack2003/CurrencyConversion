package tn.course.teterin.integration_service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.course.teterin.integration_service.dto.CurrencyListResponse;

@FeignClient(name = "currency-list-client", url = "https://api.getgeoapi.com/v2/currency")
public interface CurrencyListClient {

    @GetMapping("/list")
    CurrencyListResponse getCurrencyList(@RequestParam("api_key") String apiKey);
}
