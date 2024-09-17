package br.edu.infnet.ReceitaFacil.ingrediente.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.ReceitaFacil.ingrediente.model.Ingrediente;
import br.edu.infnet.ReceitaFacil.ingrediente.model.IngredienteResponse;
import br.edu.infnet.ReceitaFacil.ingrediente.model.ItemResponse;
import br.edu.infnet.ReceitaFacil.ingrediente.model.Medida;
import br.edu.infnet.ReceitaFacil.ingrediente.repository.IngredienteRepository;
import br.edu.infnet.ReceitaFacil.ingrediente.service.client.ItemClient;
import br.edu.infnet.ReceitaFacil.ingrediente.service.client.MedidaClient;
import br.edu.infnet.ReceitaFacil.ingrediente.service.client.ReceitaClient;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IngredienteService {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private ItemClient itemClient;
    @Autowired
    private MedidaClient medidaClient;
    @Autowired
    private ReceitaClient receitaClient;

    public int getTotalByItemId(Long id) {
        return ingredienteRepository.findByItemId(id).size();
    }

    public List<IngredienteResponse> getAll() {
        var ingredientes = ingredienteRepository.findAll();
        ItemResponse item = null;
        Medida medida = null;
        List<IngredienteResponse> listIngredientes = new ArrayList<>();
        for(Ingrediente ingrediente : ingredientes) {
            item = itemClient.getItem(ingrediente.getItemId());
            medida = medidaClient.getMedida(ingrediente.getMedidaId());
            listIngredientes.add(IngredienteResponse.builder()
                                    .id(ingrediente.getId())
                                    .quantidade(ingrediente.getQuantidade())
                                    .item(item)
                                    .medida(medida)
                                    .custo(medida.convert(item.getMedida()) * ingrediente.getQuantidade() * item.getPreco())
                                    .build()
            );
        }
        return listIngredientes;
    }

    public List<IngredienteResponse> getByReceitaId(Long id) {
        var ingredientes = ingredienteRepository.findByReceitaId(id);
        ItemResponse item = null;
        Medida medida = null;
        List<IngredienteResponse> listIngredientes = new ArrayList<>();
        for(Ingrediente ingrediente : ingredientes) {
            item = itemClient.getItem(ingrediente.getItemId());
            medida = medidaClient.getMedida(ingrediente.getMedidaId());
            listIngredientes.add(IngredienteResponse.builder()
                                    .id(ingrediente.getId())
                                    .quantidade(ingrediente.getQuantidade())
                                    .item(item)
                                    .medida(medida)
                                    .custo(medida.convert(item.getMedida()) * ingrediente.getQuantidade() * item.getPreco())
                                    .build()
            );
        }
        return listIngredientes;
    }

    public IngredienteResponse getById(Long id) {
        var ingrediente = ingredienteRepository.findById(id).orElse(null);
        var item = itemClient.getItem(ingrediente.getItemId());
        var medida = medidaClient.getMedida(ingrediente.getMedidaId());
        return IngredienteResponse.builder()
                .id(ingrediente.getId())
                .quantidade(ingrediente.getQuantidade())
                .item(item)
                .medida(medida)
                .custo(medida.convert(item.getMedida()) * ingrediente.getQuantidade() * item.getPreco())
            .build();
    }

    public Boolean addByReceitaId(Long id, IngredienteResponse ingrediente) {
        var receita = receitaClient.getReceita(id);
        if (receita == null) return false;
        ingredienteRepository.save(Ingrediente.builder()
                                    .quantidade(ingrediente.getQuantidade())
                                    .itemId(ingrediente.getItem().getId())
                                    .medidaId(ingrediente.getMedida().getId())
                                    .receitaId(id)
                                    .build());
        return true;
    }

    public Boolean update(Long receitaId, Long ingredienteId, IngredienteResponse ingrediente) {
        return ingredienteRepository.findById(ingredienteId).map(update -> {
            update.setItemId(ingrediente.getItem().getId());
            update.setQuantidade(ingrediente.getQuantidade());
            update.setMedidaId(ingrediente.getMedida().getId());
            update.setReceitaId(receitaId);
            ingredienteRepository.save(update);
            return true;
        }).orElseGet(() -> {
            var receita = receitaClient.getReceita(receitaId);
            if (receita == null) return false;
            ingredienteRepository.save(Ingrediente.builder()
                                        .id(ingrediente.getId())
                                        .quantidade(ingrediente.getQuantidade())
                                        .itemId(ingrediente.getItem().getId())
                                        .medidaId(ingrediente.getMedida().getId())
                                        .receitaId(receitaId)
                                        .build());
            return true;
        });
    }

    public Boolean delete(Long id) {
        if(ingredienteRepository.findById(id).isEmpty()) return false;
        ingredienteRepository.deleteById(id);
        return true;
    }
}
