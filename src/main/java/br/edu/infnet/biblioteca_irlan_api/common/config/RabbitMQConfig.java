package br.edu.infnet.biblioteca_irlan_api.common.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${queue.aluguel.name}")
    private String aluguelQueue;

    @Value("${queue.devolucao.name}")
    private String devolucaoQueue;

    @Bean
    public Queue aluguelQueue() {
        return new Queue(aluguelQueue, true);
    }

    @Bean
    public Queue devolucaoQueue() {
        return new Queue(devolucaoQueue, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
