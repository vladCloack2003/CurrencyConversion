package tn.course.teterin.router_service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tn.course.teterin.router_service.dto.CurrencyListResponse;

@FeignClient(name = "currency-list-client", url = "http://localhost:8081")
public interface CurrencyListClient {

    @GetMapping("/list")
    CurrencyListResponse getCurrencies();
}
