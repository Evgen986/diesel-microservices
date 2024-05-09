package ru.maliutin.storage.service;

import ru.maliutin.storage.domain.Product;

import java.util.List;

public interface KafkaProducer {

    void sendMessage(List<Product> products);

}
