package com.shopee.ecommer.configs;

import com.shopee.ecommer.constants.PathApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import static com.shopee.ecommer.constants.AuthorityConstant.ADMIN;
import static com.shopee.ecommer.constants.AuthorityConstant.CLIENT;

@Configuration
public class SecurityConfig {
    private static final String JWT_ROLE_NAME = "authorities";
    private static final String ROLE_PREFIX = "";
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    String issuerUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                PathApi.ACCOUNT + PathApi.GET_TOKEN,
                                PathApi.ACCOUNT + PathApi.GET_REFRESH_TOKEN,
                                "/swagger-ui/**", "/v3/api-docs/**",
                                "/actuator/**", PathApi.TEST + "/**",
                                "/graphql"
                        ).permitAll()
                        .requestMatchers(PathApi.ACCOUNT + "/**").hasAuthority(ADMIN)
                        .requestMatchers(PathApi.PRODUCT + "/**").hasAnyAuthority(ADMIN, CLIENT)
                        .requestMatchers(PathApi.CATEGORY + "/**").hasAnyAuthority(ADMIN, CLIENT)
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
}
