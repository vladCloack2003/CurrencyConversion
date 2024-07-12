package tn.course.teterin.integration_service.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.course.teterin.integration_service.exception.*;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> switch (response.status()) {
            case 400 -> new BadRequestException("Некорректный запрос");
            case 403 -> new ForbiddenException("Отказано: проблемы с API ключом пользователя");
            case 404 -> new NotFoundException("Не найдено: ресурс не найден или запрошен неверный формат");
            case 405 -> new MethodNotAllowedException("Запрещенный метод");
            case 500 -> new InternalServerErrorException("Ошибка сервера");
            default -> new RuntimeException("Неизвестная ошибка");
        };
    }
}
