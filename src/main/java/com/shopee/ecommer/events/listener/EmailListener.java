package com.shopee.ecommer.events.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shopee.ecommer.events.EmailEvent;
import com.shopee.ecommer.feignClient.EmailServiceClient;
import com.shopee.ecommer.models.requests.EmailDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailListener {


    protected final EmailServiceClient emailServiceClient;
    protected final RabbitTemplate rabbitTemplate;
    protected final StreamBridge streamBridge;
    @Value("${custom.rabbit-mq.queue}")
    private String queueName;

    @Async
    @EventListener
    public void emailEvent(EmailEvent event) throws InterruptedException, JsonProcessingException {
//        Rabbit MQ
//        log.info("Spring Event: event");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String queuePayloadString = objectMapper.writeValueAsString(event.getEmailDto());
//        rabbitTemplate.convertAndSend(queueName, queuePayloadString);
//        log.info("finished: " + event.getEmailDto());

//        Option 1
//        Feign Client
//        emailServiceClient.sendMail();

//        Option 2
//        Spring cloud stream
        var result = streamBridge
                .send("sendCommunication-out-0", EmailDto.builder().emailsFrom(List.of("quannguyen@gmail.com")).build());
        log.info("send successfully: {}", result);
    }

}
