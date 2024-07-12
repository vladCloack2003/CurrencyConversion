package tn.course.teterin.integration_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "currency_conversion")
@Getter
@Setter
public class CurrencyConversionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fromCurrency;

    @Column(nullable = false)
    private String toCurrency;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double result;

    @Column(nullable = false)
    private long timestamp;
}
