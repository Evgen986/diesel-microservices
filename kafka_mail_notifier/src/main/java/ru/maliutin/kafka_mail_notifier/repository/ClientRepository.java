package ru.maliutin.kafka_mail_notifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maliutin.kafka_mail_notifier.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
