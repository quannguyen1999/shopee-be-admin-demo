package com.shopee.ecommer.controllers.rest;

import com.shopee.ecommer.constants.PathApi;
import com.shopee.ecommer.models.requests.EmailDto;
import com.shopee.ecommer.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = PathApi.TEST)
@AllArgsConstructor
public class TestRestController {

    public final EmailService emailService;

    //TODO Only for testing email (No Expose)
    @GetMapping
    public ResponseEntity<String> testMail() {
        emailService.sendMail(EmailDto.builder()
                        .emailsFrom(List.of("nguyendanganhquan99@gmail.com"))
                .build());
        return ResponseEntity.ok("success");
    }
}
