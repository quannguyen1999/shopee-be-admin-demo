package com.shopee.shopeebeadmindemo.configs;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class GraphQlScalarConfig {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Bean
    public GraphQLScalarType dateScalar() {
        return GraphQLScalarType.newScalar()
                .name("Date")
                .description("Date as scalar.")
                .coercing(new Coercing<>() {
                    @Override
                    public String serialize(Object input) {
                        return parseObjectToString(input);
                    }
                    @Override
                    public Date parseValue(Object input) {
                        return parseObjectToDate(input);
                    }
                    @Override
                    public Date parseLiteral(Object input) {
                        return parseObjectToDate(input);
                    }
                }).build();
    }

    private static Date parseObjectToDate(Object input) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(((StringValue) input).getValue());
        } catch (ParseException e) {
            throw new CoercingParseLiteralException("Invalid value to parse: " + input);
        }
    }

    private static String parseObjectToString(Object input) {
        return new SimpleDateFormat(DATE_FORMAT).format(input);
    }

    // https://docs.spring.io/spring-graphql/docs/1.1.0-RC1/reference/html/#execution-graphqlsource-runtimewiring-configurer
    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        GraphQLScalarType scalarType = dateScalar();
        return wiringBuilder -> wiringBuilder.scalar(scalarType);
    }


}
