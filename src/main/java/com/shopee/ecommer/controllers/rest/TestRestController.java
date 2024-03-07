package com.shopee.ecommer.controllers.rest;

import com.shopee.ecommer.constants.PathApi;
import com.shopee.ecommer.models.requests.EmailDto;
import com.shopee.ecommer.services.EmailService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "Test Rest",
        description = "Test Email details"
)
@RestController
@RequestMapping(value = PathApi.TEST)
@AllArgsConstructor
public class TestRestController {

    public final EmailService emailService;

    //TODO Only for testing email (No Expose)
    @RateLimiter(name = "testMail")
    @GetMapping
    public ResponseEntity<String> testMail() {
        emailService.sendMail(EmailDto.builder()
                .emailsFrom(List.of("nguyendanganhquan99@gmail.com"))
                .build());
        return ResponseEntity.ok("success");
    }
}
