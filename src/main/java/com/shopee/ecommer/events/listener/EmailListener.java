package com.shopee.ecommer.events.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopee.ecommer.events.EmailEvent;
import com.shopee.ecommer.feignClient.EmailServiceClient;
import com.shopee.ecommer.models.requests.EmailDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailListener {


    @Value("${custom.rabbit-mq.queue}")
    private String queueName;

    protected final EmailServiceClient emailServiceClient;

    protected final RabbitTemplate rabbitTemplate;


    @Async
    @EventListener
    public void emailEvent(EmailEvent event) throws InterruptedException, JsonProcessingException {
//        Rabbit MQ
//        log.info("Spring Event: event");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String queuePayloadString = objectMapper.writeValueAsString(event.getEmailDto());
//        rabbitTemplate.convertAndSend(queueName, queuePayloadString);
//        log.info("finished: " + event.getEmailDto());

//        Feign Client
        emailServiceClient.sendMail();

    }

}
