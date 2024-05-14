package ru.maliutin.kafka_mail_notifier.service.impl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maliutin.kafka_mail_notifier.domain.Client;
import ru.maliutin.kafka_mail_notifier.domain.Product;
import ru.maliutin.kafka_mail_notifier.repository.ClientRepository;
import ru.maliutin.kafka_mail_notifier.service.ClientService;
import ru.maliutin.kafka_mail_notifier.service.MailService;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final MailService mailService;

    @Override
    @SneakyThrows
    @Transactional(readOnly = true)
    public void sendMessageAllClients(List<Product> products) {
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients){
            mailService.sendMessage(client.getEmail(), products);
        }
    }
}
