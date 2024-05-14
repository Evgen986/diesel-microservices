package ru.maliutin.kafka_mail_notifier.service.impl;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.maliutin.kafka_mail_notifier.domain.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MailServiceImplTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private TemplateEngine templateEngine;

    @InjectMocks
    private MailServiceImpl mailService;

    @Test
    @SneakyThrows
    public void sendMessageExceptSendMessage(){
        String email = "some@email.com";
        Product product = new Product("some product", 1, new BigDecimal(1));
        when(mailSender.createMimeMessage()).thenReturn(Mockito.mock(MimeMessage.class));
        when(templateEngine.process(anyString(), any(Context.class))).thenReturn("context");
        doNothing().when(mailSender).send(any(MimeMessage.class));

        mailService.sendMessage(email, List.of(product));

        verify(mailSender).createMimeMessage();
        verify(templateEngine).process(anyString(), any(Context.class));
        verify(mailSender).send(any(MimeMessage.class));
    }

}
