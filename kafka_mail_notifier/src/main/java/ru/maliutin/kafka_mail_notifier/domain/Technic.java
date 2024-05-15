package ru.maliutin.kafka_mail_notifier.domain;

/**
 * Класс техники товара.
 * @param technicId идентификатор техники.
 * @param title название техники.
 */
public record Technic(Long technicId, String title) {
}
