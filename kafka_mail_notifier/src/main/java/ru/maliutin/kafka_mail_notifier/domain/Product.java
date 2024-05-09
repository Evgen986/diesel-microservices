package ru.maliutin.kafka_mail_notifier.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Product(String title, int balance, BigDecimal price) {
}
