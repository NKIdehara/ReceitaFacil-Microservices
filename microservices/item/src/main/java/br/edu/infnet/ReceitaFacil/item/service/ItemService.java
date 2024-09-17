package br.edu.infnet.ReceitaFacil.item.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.ReceitaFacil.item.model.Item;
import br.edu.infnet.ReceitaFacil.item.model.ItemResponse;
import br.edu.infnet.ReceitaFacil.item.model.Medida;
import br.edu.infnet.ReceitaFacil.item.repository.ItemRepository;
import br.edu.infnet.ReceitaFacil.item.service.client.IngredienteClient;
import br.edu.infnet.ReceitaFacil.item.service.client.MedidaClient;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MedidaClient medidaClient;

    @Autowired
    private IngredienteClient ingredienteClient;

    public List<ItemResponse> getAll() {
        var items = itemRepository.findAll();
        Medida medida = null;
        List<ItemResponse> listItems = new ArrayList<>();
        for(Item item : items) {
            medida = medidaClient.getMedida(item.getMedidaId());
            listItems.add(ItemResponse.builder()
                        .id(item.getId())
                        .descricao(item.getDescricao())
                        .preco(item.getPreco())
                        .medida(medida)
                        .build()
            );
        }
        return listItems;
    }

    public ItemResponse getById(Long id) {
        var item = itemRepository.findById(id).orElse(null);
        var medida = medidaClient.getMedida(item.getMedidaId());
        return ItemResponse.builder()
                .id(item.getId())
                .descricao(item.getDescricao())
                .preco(item.getPreco())
                .medida(medida)
                .build();
    }

    public Long add(ItemResponse item) {
        return itemRepository.save(Item.builder()
                                    .id(item.getId())
                                    .descricao(item.getDescricao())
                                    .preco(item.getPreco())
                                    .medidaId(item.getMedida().getId())
                                    .build()
        ).getId();
    }

    public Boolean delete(Long id) {
        if(itemRepository.findById(id).isEmpty()) return false;
        if(ingredienteClient.getTotalIngredienteByItemId(id) > 0) return false;
        itemRepository.deleteById(id);
        return true;
    }
}