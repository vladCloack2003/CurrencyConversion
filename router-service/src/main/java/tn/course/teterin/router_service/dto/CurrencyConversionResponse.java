package tn.course.teterin.router_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CurrencyConversionResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("updated_date")
    private String updatedDate;

    @JsonProperty("base_currency_code")
    private String baseCurrencyCode;

    @JsonProperty("base_currency_name")
    private String baseCurrencyName;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("rates")
    private Map<String, CurrencyRate> rates;

    @Getter
    @Setter
    public static class CurrencyRate {

        @JsonProperty("currency_name")
        private String currencyName;

        @JsonProperty("rate")
        private String rate;

        @JsonProperty("rate_for_amount")
        private String rateForAmount;
    }
}
