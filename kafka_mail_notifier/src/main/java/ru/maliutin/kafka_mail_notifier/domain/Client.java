package ru.maliutin.kafka_mail_notifier.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    @Column(name = "c_firstname")
    private String firstname;
    @Column(name = "c_surname")
    private String surname;
    @Column(name = "c_email")
    private String email;

}
