package br.edu.infnet.ReceitaFacil.ingrediente.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.ReceitaFacil.ingrediente.model.Receita;

@FeignClient("RECEITA-SERVICE")
public interface ReceitaClient {
    @GetMapping("/receita/{id}")
    Receita getReceita(@PathVariable Long id);
}
