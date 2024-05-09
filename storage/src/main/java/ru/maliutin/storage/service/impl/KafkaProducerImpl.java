package ru.maliutin.storage.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.maliutin.storage.domain.Product;
import ru.maliutin.storage.service.KafkaProducer;

import java.util.List;
@Service
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {

    private final KafkaTemplate<Object, String> kafkaTemplate;
    @Override
    @SneakyThrows
    public void sendMessage(List<Product> products) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonProducts = objectMapper.writeValueAsString(products);
        kafkaTemplate.send("newProducts", jsonProducts);
    }
}
