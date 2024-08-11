package br.edu.infnet.ReceitaFacil.item.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.ReceitaFacil.item.model.Medida;

@FeignClient("MEDIDA-SERVICE")
public interface MedidaClient {
    @GetMapping("/medida/{id}")
    Medida getMedida(@PathVariable Long id);
}