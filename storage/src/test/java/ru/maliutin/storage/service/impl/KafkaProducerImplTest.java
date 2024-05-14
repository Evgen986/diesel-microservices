package ru.maliutin.storage.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import ru.maliutin.storage.domain.Product;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerImplTest {
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;
    @InjectMocks
    private KafkaProducerImpl kafkaProducer;

    @Test
    public void sendMessageExpectCorrect(){
        List<Product> products = List.of(new Product());
        doAnswer(invocation -> null).when(kafkaTemplate).send(anyString(), anyString());

        kafkaProducer.sendMessage(products);

        verify(kafkaTemplate).send(eq("newProducts"), anyString());
    }
}
