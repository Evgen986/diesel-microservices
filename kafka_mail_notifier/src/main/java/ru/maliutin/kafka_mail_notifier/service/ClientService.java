package ru.maliutin.kafka_mail_notifier.service;


import ru.maliutin.kafka_mail_notifier.domain.Product;

import java.util.List;

public interface ClientService {

    void sendMessageAllClients(List<Product> products);
}
