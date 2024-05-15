package ru.maliutin.kafka_mail_notifier.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.maliutin.kafka_mail_notifier.domain.Product;
import ru.maliutin.kafka_mail_notifier.service.MailService;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Сервис рассылки писем.
 */
@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    /**
     * Интерфейс Spring Framework,
     * предназначенный для отправки электронных писем из приложений.
     */
    private final JavaMailSender mailSender;
    /**
     * Поле шаблонизатора Thymeleaf.
     */
    private final TemplateEngine templateEngine;


    /**
     * Метод отправки письма.
     * @param email адрес назначения.
     * @param products список товаров.
     * @throws MessagingException исключения при отправке писем.
     */
    @Override
    @Async("mailExecutor")
    public void sendMessage(String email, List<Product> products) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                false, "UTF-8");
        helper.setSubject(
                "Ура! У нас появился новый товар!");
        helper.setTo(email);
        Context context = new Context();
        context.setVariable("products", products);
        String emailContent = templateEngine.process(
                "mail/new_product.html", context);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }
}
