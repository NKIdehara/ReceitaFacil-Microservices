package br.edu.infnet.ReceitaFacil.publicacao.message;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import br.edu.infnet.ReceitaFacil.publicacao.model.Publicacao;
import br.edu.infnet.ReceitaFacil.publicacao.model.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class PublicacaoConsumer implements Serializable {
    private final PublicacaoProducer publicacaoProducer;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = {"receitafacil-criada"}, ackMode = "MANUAL")
    public Boolean receive(@Payload String json, Channel channel, Message message) {
        Publicacao publicacao = null;
        try {
            if (json.isEmpty()) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                return null;
            };
            publicacao = objectMapper.readValue(json, Publicacao.class);
            publicacao.setStatus(Status.PUBLICADA);
            publicacaoProducer.send(publicacao);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("Receita enviada para publicação.");
            log.info(objectMapper.writeValueAsString(publicacao));
        } catch (AmqpException | IOException e) {
            log.error("Erro ao enviar para publicação: " + e.getMessage());
            try {
                publicacaoProducer.error("Erro ao enviar para publicação: " + e.getMessage());
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (AmqpException | IOException err) {
                log.info("Erro ao enviar para publicação: " + err.getMessage());
            }
            return false;
        }
        return true;
    }
}
