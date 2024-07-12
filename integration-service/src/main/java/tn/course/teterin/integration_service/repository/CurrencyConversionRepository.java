package tn.course.teterin.integration_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.course.teterin.integration_service.entity.CurrencyConversionEntity;

public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversionEntity, Long> {
}
