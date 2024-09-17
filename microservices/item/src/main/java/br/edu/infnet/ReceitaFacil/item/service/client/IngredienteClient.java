package br.edu.infnet.ReceitaFacil.item.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("INGREDIENTE-SERVICE")
public interface IngredienteClient {
    @GetMapping("/ingrediente/total/item/{id}")
    Long getTotalIngredienteByItemId(@PathVariable Long id);
}