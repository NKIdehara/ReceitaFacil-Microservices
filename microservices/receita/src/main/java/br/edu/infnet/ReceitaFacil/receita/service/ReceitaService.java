package br.edu.infnet.ReceitaFacil.receita.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.edu.infnet.ReceitaFacil.receita.model.IngredienteResponse;
import br.edu.infnet.ReceitaFacil.receita.model.Publicacao;
import br.edu.infnet.ReceitaFacil.receita.model.Receita;
import br.edu.infnet.ReceitaFacil.receita.model.ReceitaResponse;
import br.edu.infnet.ReceitaFacil.receita.model.Status;
import br.edu.infnet.ReceitaFacil.receita.repository.PublicacaoRepository;
import br.edu.infnet.ReceitaFacil.receita.repository.ReceitaRepository;
import br.edu.infnet.ReceitaFacil.receita.service.client.IngredienteClient;
import br.edu.infnet.ReceitaFacil.receita.service.message.PublicacaoProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private IngredienteClient ingredienteClient;

    private final PublicacaoProducer publicacaoProducer;

    public List<ReceitaResponse> getAll() {
        var receitas = receitaRepository.findAll();
        List<ReceitaResponse> listReceitas = new ArrayList<>();
        for(Receita receita : receitas) {
            List<IngredienteResponse> ingredientes = ingredienteClient.getIngredientesByReceitaId(receita.getId());
            listReceitas.add(ReceitaResponse.builder()
                                .id(receita.getId())
                                .usuario(receita.getUsuario())
                                .nome(receita.getNome())
                                .preparo(receita.getPreparo())
                                .figura(receita.getFigura())
                                .ingredientes(ingredientes)
                                .status(receita.getStatus())
                                .createdDate(receita.getCreatedDate())
                                .createdBy(receita.getCreatedBy())
                                .lastModifiedDate(receita.getLastModifiedDate())
                                .lastModifiedBy(receita.getLastModifiedBy())
                                .custo(receita.getCusto(ingredientes))
                                .build()
            );
        }
        return listReceitas;
    }

    public ReceitaResponse getById(Long id) {
        var receita = receitaRepository.findById(id).orElse(null);
        List<IngredienteResponse> ingredientes = ingredienteClient.getIngredientesByReceitaId(id);
        return ReceitaResponse.builder()
                .id(receita.getId())
                .usuario(receita.getUsuario())
                .nome(receita.getNome())
                .preparo(receita.getPreparo())
                .figura(receita.getFigura())
                .ingredientes(ingredientes)
                .status(receita.getStatus())
                .createdDate(receita.getCreatedDate())
                .createdBy(receita.getCreatedBy())
                .lastModifiedDate(receita.getLastModifiedDate())
                .lastModifiedBy(receita.getLastModifiedBy())
                .custo(receita.getCusto(ingredientes))
                .build();
    }

    public List<ReceitaResponse> getByUsuarioId(String uid) {
        var receitas = receitaRepository.findByUsuario(uid);
        List<ReceitaResponse> listReceitas = new ArrayList<>();
        for(Receita receita : receitas) {
            List<IngredienteResponse> ingredientes = ingredienteClient.getIngredientesByReceitaId(receita.getId());
            listReceitas.add(ReceitaResponse.builder()
                                .id(receita.getId())
                                .usuario(receita.getUsuario())
                                .nome(receita.getNome())
                                .preparo(receita.getPreparo())
                                .figura(receita.getFigura())
                                .ingredientes(ingredientes)
                                .status(receita.getStatus())
                                .createdDate(receita.getCreatedDate())
                                .createdBy(receita.getCreatedBy())
                                .lastModifiedDate(receita.getLastModifiedDate())
                                .lastModifiedBy(receita.getLastModifiedBy())
                                .custo(receita.getCusto(ingredientes))
                                .build()
            );
        }
        return listReceitas;
    }

    public Long add(Receita receita) {
        receita.setStatus(Status.CRIADA);
        Long id = receitaRepository.save(receita).getId();
        Publicacao publicacao = Publicacao.builder()
                                    .uuid(UUID.randomUUID())
                                    .receitaId(id)
                                    .status(Status.CRIADA)
                                    .build();
        publicacaoRepository.save(publicacao);
        log.info("Receita '{}': {}", receita.getNome(), publicacao.getStatus());
        try {
            publicacaoProducer.send(publicacao);
        } catch (JsonProcessingException | AmqpException e) {
            log.info("Erro ao publicar receita: " + e.getMessage());
            try {
                publicacaoProducer.error("Erro ao publicar receita: " + e.getMessage());
            } catch (JsonProcessingException | AmqpException err) {
                log.info("Erro ao publicar receita: " + err.getMessage());
            }
        }
        return id;
    }

    public Boolean update(Long id, Receita receita) {
        return receitaRepository.findById(id).map(update -> {
            update.setUsuario(receita.getUsuario());
            update.setNome(receita.getNome());
            update.setPreparo(receita.getPreparo());
            update.setFigura(receita.getFigura());
            update.setStatus(Status.ALTERADA);
            receitaRepository.save(update);
            Publicacao publicacao = Publicacao.builder()
                .uuid(UUID.randomUUID())
                .receitaId(id)
                .status(Status.ALTERADA)
                .build();
            publicacaoRepository.save(publicacao);
            log.info("Receita '{}': {}", receita.getNome(), publicacao.getStatus());
            try {
                publicacaoProducer.send(publicacao);
            } catch (JsonProcessingException | AmqpException e) {
                log.info("Erro ao publicar receita: " + e.getMessage());
                try {
                    publicacaoProducer.error("Erro ao publicar receita: " + e.getMessage());
                } catch (JsonProcessingException | AmqpException err) {
                    log.info("Erro ao publicar receita: " + err.getMessage());
                }
            }
            return true;
        }).orElseGet(() -> {
            return false;
        });
    }

    public Boolean delete(Long id) {
        if(receitaRepository.findById(id).isEmpty()) return false;
        receitaRepository.deleteById(id);
        return true;
    }

    public Status getStatus(Long id) {
        Publicacao publicacao = publicacaoRepository.findByReceitaId(id);
        if (publicacao == null) return Status.NAO_ENCONTRADA;
        return publicacao.getStatus();
    }
}