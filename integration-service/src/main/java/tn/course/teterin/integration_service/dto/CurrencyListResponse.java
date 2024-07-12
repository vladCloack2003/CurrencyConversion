package tn.course.teterin.integration_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyListResponse {
    private String status;
    private Map<String, String> currencies;
}
