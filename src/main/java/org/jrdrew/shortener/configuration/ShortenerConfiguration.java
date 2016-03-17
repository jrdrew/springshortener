/**
 * Copyright (c) 2014-2015 Cazena, Inc., as an unpublished work.
 * This notice does not imply unrestricted or public access to these
 * materials which are a trade secret of Cazena, Inc. or its
 * subsidiaries or affiliates (together referred to as "Cazena"), and
 * which may not be copied, reproduced, used, sold or transferred to any
 * third party without Cazena's prior written consent.
 * <p>
 * All rights reserved.
 * Created by jondrew on 11/25/15.
 */
package org.jrdrew.shortener.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@ComponentScan("org.jrdrew.shortener")
public class ShortenerConfiguration {

    @Value("${db.jdbcUrl}")
    private String jdbcUrl;

    @Value("${db.username}")
    private String jdbcUsername;

    @Value("${db.password}")
    private String jdbcPassword;

    @Bean
    public static PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(false);
        propertyPlaceholderConfigurer.setLocation(new FileSystemResource("/cazena/config/sgloader/manager.properties"));
        return propertyPlaceholderConfigurer;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl(jdbcUrl);
        driverManagerDataSource.setUsername(jdbcUsername);
        driverManagerDataSource.setPassword(jdbcPassword);
        return driverManagerDataSource;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);

        messageConverters.add(mappingJackson2HttpMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}
