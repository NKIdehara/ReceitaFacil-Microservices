package br.edu.infnet.ReceitaFacil.publicacao.message;

import java.io.Serializable;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @RabbitListener(queues = {"receitafacil-criada"})
    public Publicacao receive(@Payload String json) throws JsonProcessingException, AmqpException {
        Publicacao publicacao = null;
        try {
            publicacao = objectMapper.readValue(json, Publicacao.class);
            publicacao.setStatus(Status.PUBLICADA);
            publicacaoProducer.send(publicacao);
            log.info("Receita: {}", publicacao.getStatus());
        } catch (JsonProcessingException e) {
            publicacaoProducer.error(publicacao);
            log.error(e.getMessage());
        }
        return publicacao;
    }
}
