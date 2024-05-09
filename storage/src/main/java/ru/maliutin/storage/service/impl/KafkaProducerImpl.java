package ru.maliutin.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.maliutin.storage.domain.Product;
import ru.maliutin.storage.service.KafkaProducer;

import java.util.List;
@Service
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {

    private final KafkaTemplate<String, List<Product>> kafkaTemplate;
    @Override
    public void sendMessage(List<Product> products) {
        kafkaTemplate.send("newProducts", products);
    }
}
