package br.edu.infnet.ReceitaFacil.receita.service.message;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import br.edu.infnet.ReceitaFacil.receita.model.Publicacao;
import br.edu.infnet.ReceitaFacil.receita.service.ReceitaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class PublicacaoConsumer implements Serializable {
    private final PublicacaoProducer publicacaoProducer;
    private final ObjectMapper objectMapper;
    private final ReceitaService receitaService;

    @RabbitListener(queues = {"receitafacil-publicada"}, ackMode = "MANUAL")
    public Boolean receive(@Payload String json, Channel channel, Message message) {
        Publicacao publicacao = null;
        try {
            if (json.isEmpty()) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                return null;
            };
            publicacao = objectMapper.readValue(json, Publicacao.class);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info(objectMapper.writeValueAsString(publicacao));
            log.info("Receita '{}': {}", receitaService.getById(publicacao.getReceitaId()).getNome(), publicacao.getStatus());
        } catch (IOException e) {
            log.info("Erro ao publicar receita: " + e.getMessage());
            try {
                publicacaoProducer.error("Erro ao publicar receita: " + e.getMessage());
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (AmqpException | IOException err) {
                log.info("Erro ao publicar receita: " + err.getMessage());
            }
            return false;
        }
        return true;
    }
}
