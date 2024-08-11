package br.edu.infnet.ReceitaFacil.ingrediente.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.ReceitaFacil.ingrediente.model.ItemResponse;

@FeignClient("ITEM-SERVICE")
public interface ItemClient {
    @GetMapping("/item/{id}")
    ItemResponse getItem(@PathVariable Long id);
}