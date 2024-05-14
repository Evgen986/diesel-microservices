package ru.maliutin.kafka_mail_notifier.service.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.maliutin.kafka_mail_notifier.domain.Client;
import ru.maliutin.kafka_mail_notifier.domain.Product;
import ru.maliutin.kafka_mail_notifier.repository.ClientRepository;
import ru.maliutin.kafka_mail_notifier.service.MailService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private MailService mailService;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    @SneakyThrows
    public void sendMessageAllClientExceptSendMessages(){
        Client client = new Client();
        client.setEmail("some email");
        List<Client> clients = List.of(
                client, client
        );
        List<Product> products = List.of(
                new Product("title", 1, new BigDecimal(1))
        );
        when(clientRepository.findAll()).thenReturn(clients);
        doNothing().when(mailService).sendMessage(anyString(), anyList());

        clientService.sendMessageAllClients(products);
        verify(clientRepository).findAll();
        verify(mailService, times(clients.size())).sendMessage(anyString(), anyList());
    }

}
