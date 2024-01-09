package com.shopee.shopeebeadmindemo.configs;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.util.ObjectUtils;

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
                        return parseDateObjectToString(input);
                    }

                    @Override
                    public Date parseValue(Object input) {
                        return parseDateObjectToDate(input);
                    }

                    @Override
                    public Date parseLiteral(Object input) {
                        return parseDateObjectToDate(input);
                    }
                }).build();
    }

    @Bean
    public GraphQLScalarType doubleScalar() {
        return GraphQLScalarType.newScalar()
                .name("Double")
                .description("Double as scalar.")
                .coercing(new Coercing<>() {
                    @Override
                    public Double serialize(Object input) {
                        return parseObjectToDouble(input);
                    }

                    @Override
                    public Double parseValue(Object input) {
                        return parseObjectToDouble(input);
                    }

                    @Override
                    public Double parseLiteral(Object input) {
                        return parseObjectToDouble(input);
                    }
                }).build();
    }

    @Bean
    public GraphQLScalarType byteArrayScalar() {
        return GraphQLScalarType.newScalar()
                .name("ByteArray")
                .description("ByteArray as scalar.")
                .coercing(new Coercing<>() {
                    @Override
                    public String serialize(Object input) {
                        return parseByArrayObjectToString(input);
                    }

                    @Override
                    public byte[] parseValue(Object input) {
                        return parseByteArrayObjectToByteArray(input);
                    }

                    @Override
                    public byte[] parseLiteral(Object input) {
                        return parseByteArrayObjectToByteArray(input);
                    }
                }).build();
    }

    private static Date parseDateObjectToDate(Object input) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(((StringValue) input).getValue());
        } catch (ParseException e) {
            throw new CoercingParseLiteralException("Invalid value to parse: " + input);
        }
    }

    private static Double parseObjectToDouble(Object input) {
        return ObjectUtils.isEmpty(input) ? 0 : Double.parseDouble(input.toString());
    }

    private static String parseDateObjectToString(Object input) {
        return new SimpleDateFormat(DATE_FORMAT).format(input);
    }

    private static byte[] parseByteArrayObjectToByteArray(Object input) {
        if (input instanceof StringValue) {
            return ((StringValue) input).getValue().getBytes();
        }
        throw new CoercingParseLiteralException("Expected a StringValue.");
    }

    private static String parseByArrayObjectToString(Object input) {
        if (!(input instanceof byte[])) {
            throw new CoercingSerializeException("Expected a byte array.");
        }
        return new String((byte[]) input);
    }


    // https://docs.spring.io/spring-graphql/docs/1.1.0-RC1/reference/html/#execution-graphqlsource-runtimewiring-configurer
    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurerDate() {
        GraphQLScalarType scalarType = dateScalar();
        return wiringBuilder -> wiringBuilder.scalar(scalarType);
    }

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurerByteArray() {
        GraphQLScalarType scalarType = byteArrayScalar();
        return wiringBuilder -> wiringBuilder.scalar(scalarType);
    }

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurerDouble() {
        GraphQLScalarType scalarType = doubleScalar();
        return wiringBuilder -> wiringBuilder.scalar(scalarType);
    }


}
