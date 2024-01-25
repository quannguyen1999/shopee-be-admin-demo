package com.shopee.ecommer.configs;

import com.shopee.ecommer.constants.PathApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@EnableWebSecurity
public class SecurityConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    String issuerUri;
    private static final String JWT_ROLE_NAME = "authorities";
    private static final String ROLE_PREFIX = "";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PathApi.ACCOUNT + PathApi.LIST)
                        .access(hasScope("message:xx"))
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(
                        oauth2 -> oauth2
                                .jwt(jwt -> jwt
                                        .decoder(JwtDecoders.fromIssuerLocation(issuerUri))
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter())

                                )

                )
                .rememberMe(Customizer.withDefaults())
                .csrf().disable()
        ;

        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
                JwtDecoders.fromIssuerLocation(issuerUri);

        OAuth2TokenValidator<Jwt> audienceValidator = audienceValidator();
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    @Bean
    OAuth2TokenValidator<Jwt> audienceValidator() {
        return new AudienceValidator();
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
}
