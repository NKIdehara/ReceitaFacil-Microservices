package br.edu.infnet.ReceitaFacil.receita.service.message;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.infnet.ReceitaFacil.receita.model.Publicacao;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublicacaoProducer {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;
    
    public void send(Publicacao publicacao) throws JsonProcessingException, AmqpException {
        amqpTemplate.convertAndSend("receitafacil-criada.exc", "receitafacil-criada.rk", objectMapper.writeValueAsString(publicacao));
    }
    public void error(Publicacao publicacao) throws JsonProcessingException, AmqpException {
        amqpTemplate.convertAndSend("receitafacil-erro.exc", "receitafacil-erro.rk", objectMapper.writeValueAsString(publicacao));
    }
}