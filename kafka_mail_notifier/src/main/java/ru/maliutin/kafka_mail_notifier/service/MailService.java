package ru.maliutin.kafka_mail_notifier.service;

import jakarta.mail.MessagingException;
import ru.maliutin.kafka_mail_notifier.domain.Product;

import java.util.List;

public interface MailService {

    void sendMessage(String email, List<Product> products) throws MessagingException;

}
