package br.edu.infnet.ReceitaFacil.receita.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.ReceitaFacil.receita.model.IngredienteResponse;
import br.edu.infnet.ReceitaFacil.receita.model.Receita;
import br.edu.infnet.ReceitaFacil.receita.model.ReceitaResponse;
import br.edu.infnet.ReceitaFacil.receita.repository.ReceitaRepository;
import br.edu.infnet.ReceitaFacil.receita.service.client.IngredienteClient;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private IngredienteClient ingredienteClient;

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
                                .createdDate(receita.getCreatedDate())
                                .createdBy(receita.getCreatedBy())
                                .lastModifiedDate(receita.getLastModifiedDate())
                                .lastModifiedBy(receita.getLastModifiedBy())
                                .custo(receita.getCusto())
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
                .createdDate(receita.getCreatedDate())
                .createdBy(receita.getCreatedBy())
                .lastModifiedDate(receita.getLastModifiedDate())
                .lastModifiedBy(receita.getLastModifiedBy())
                .custo(receita.getCusto())
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
                                .createdDate(receita.getCreatedDate())
                                .createdBy(receita.getCreatedBy())
                                .lastModifiedDate(receita.getLastModifiedDate())
                                .lastModifiedBy(receita.getLastModifiedBy())
                                .custo(receita.getCusto())
                                .build()
            );
        }
        return listReceitas;
    }

    public Long add(Receita receita) {
        return receitaRepository.save(receita).getId();
    }

    public Boolean update(Long id, Receita receita) {
        return receitaRepository.findById(id).map(update -> {
            update.setUsuario(receita.getUsuario());
            update.setNome(receita.getNome());
            update.setPreparo(receita.getPreparo());
            // update.setIngredientes(receita.getIngredientes());
            update.setFigura(receita.getFigura());
            receitaRepository.save(update);
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
}