package ru.maliutin.kafka_mail_notifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaMailNotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaMailNotifierApplication.class, args);
	}

}
