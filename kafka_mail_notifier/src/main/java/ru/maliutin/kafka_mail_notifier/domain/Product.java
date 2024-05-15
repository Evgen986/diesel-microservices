package ru.maliutin.kafka_mail_notifier.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * Класс товара.
 * @param title название товара.
 * @param balance остаток товара.
 * @param price стоимость товара.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Product(String title, int balance, BigDecimal price) {
}
