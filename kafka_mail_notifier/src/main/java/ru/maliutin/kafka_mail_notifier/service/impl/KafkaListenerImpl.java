package ru.maliutin.kafka_mail_notifier.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.maliutin.kafka_mail_notifier.domain.Product;
import ru.maliutin.kafka_mail_notifier.service.ClientService;
import ru.maliutin.kafka_mail_notifier.service.KafkaListener;

import java.util.Arrays;
import java.util.List;

/**
 * Сервис получения сообщений из топика Kafka.
 */
@Service
@AllArgsConstructor
public class KafkaListenerImpl implements KafkaListener {

    private final ClientService clientService;

    /**
     * Получение сообщений из Kafka.
     * @param message полученное сообщение.
     */
    @Override
    @SneakyThrows
    @org.springframework.kafka.annotation.KafkaListener(topics = "newProducts",groupId = "diesel")
    public void getMessage(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        Product[] product = objectMapper.readValue(message, Product[].class);
        clientService.sendMessageAllClients(List.of(product));
    }
}
