package ru.maliutin.kafka_mail_notifier.testConrol;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maliutin.kafka_mail_notifier.repository.ClientRepository;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController {

    private final ClientRepository clientRepository;

    @GetMapping()
    public String getAllClient(){
        return clientRepository.findAll().toString();
    }


}
