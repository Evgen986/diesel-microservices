package ru.maliutin.kafka_mail_notifier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Конфигурационный класс многопоточности.
 */
@Configuration
public class AsyncConfig {

    /**
     * Пул потоков.
     * @return объект управляющий пулом потоков.
     */
    @Bean(name = "mailExecutor")
    public Executor mailExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("MailThread-");
        executor.initialize();
        return executor;
    }
}
