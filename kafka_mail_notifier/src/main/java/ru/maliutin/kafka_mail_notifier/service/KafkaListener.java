package ru.maliutin.kafka_mail_notifier.service;

import ru.maliutin.kafka_mail_notifier.domain.Product;

import java.util.List;

public interface KafkaListener {

    void getMessage(String message);

}
