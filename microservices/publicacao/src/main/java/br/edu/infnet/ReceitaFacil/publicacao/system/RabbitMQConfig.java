package br.edu.infnet.ReceitaFacil.publicacao.system;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // Parameters:
    // durable = true
    // exclusive = false
    // autoDelete = false
    @Bean
    public Queue criada() {
        return new Queue("receitafacil-criada", true, false, false);
    }

    @Bean
    public Queue publicada() {
        return new Queue("receitafacil-publicada", true, false, false);
    }

    @Bean
    public Queue erro() {
        return new Queue("receitafacil-erro", true, false, false);
    }
}
