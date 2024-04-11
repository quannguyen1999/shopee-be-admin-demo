package com.shopee.ecommer.configs;

import com.shopee.ecommer.constants.PathApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;

import static com.shopee.ecommer.constants.AuthorityConstant.*;
import static com.shopee.ecommer.constants.PathApi.*;

@Configuration
public class SecurityConfig {

    private static final List<String> ALLOW_REQUEST = Arrays.asList("/css/**",
            PathApi.ACCOUNT + PathApi.GET_TOKEN,
            PathApi.ACCOUNT + PathApi.GET_REFRESH_TOKEN,
            PathApi.ACCOUNT + REGISTER,
            PathApi.ACCOUNT + PathApi.TEST,
            PathApi.ACCOUNT + OTP,
            "/swagger-ui/**", "/v3/api-docs/**",
            "/actuator/**",
            "/graphql"
    );

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    String issuerUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(ALLOW_REQUEST.toArray(new String[0])).permitAll()
                        .requestMatchers(PathApi.ACCOUNT + FULL_PATH).hasAuthority(ADMIN)
                        .requestMatchers(PathApi.ACCOUNT + PathApi.GET_INFO).hasAnyAuthority(ADMIN, CLIENT)
                        .requestMatchers(PathApi.PRODUCT + FULL_PATH).hasAnyAuthority(ADMIN, CLIENT)
                        .requestMatchers(PathApi.CATEGORY + FULL_PATH).hasAnyAuthority(ADMIN, CLIENT)
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(
                        oauth2 -> oauth2
                                .jwt(jwt -> jwt
                                        .decoder(JwtDecoders.fromIssuerLocation(issuerUri))
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter())
                                )
                )
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(JWT_ROLE_NAME);
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(ROLE_PREFIX);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler =
                new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return expressionHandler;
    }
}
