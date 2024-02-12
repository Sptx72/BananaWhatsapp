package com.banana.bananawhatsapp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import({PropertiesConfig.class, /*PropertiesConfigDev.class*/})
@ComponentScan({"com.banana.bananawhatsapp.controladores", "com.banana.bananawhatsapp.servicios", "com.banana.bananawhatsapp.persistencia"})
@EnableJpaRepositories(basePackages = {"com.banana.bananawhatsapp.persistencia"})
@EnableAutoConfiguration
@EntityScan("com.banana.bananawhatsapp.modelos")
public class SpringConfig {

}
