package ru.maliutin.storage.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация Kafka.
 */
@Configuration
public class KafkaConfiguration {
    /**
     * Создание нового топика.
     * @return бин топика.
     */
    @Bean
    public NewTopic newTopic() {
        return new NewTopic("newProducts", 1, (short) 1);
    }

}
