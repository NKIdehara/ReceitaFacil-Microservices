package br.edu.infnet.ReceitaFacil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.ReceitaFacil.model.Item;
import br.edu.infnet.ReceitaFacil.repository.ItemRepository;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Long add(Item item) {
        return itemRepository.save(item).getId();
    }

    public Boolean delete(Long id) {
        if(itemRepository.findById(id).isEmpty()) return false;
        itemRepository.deleteById(id);
        return true;
    }
}