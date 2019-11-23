package com.papita.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @version 1.0.0
 */
@Configuration
@ConditionalOnWebApplication
public class WebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    @Bean
    public WebSocketConfigurator customSpringConfigurator() {
        return new WebSocketConfigurator(); // This is just to get context
    }

}