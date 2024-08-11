package br.edu.infnet.ReceitaFacil.receita.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.ReceitaFacil.receita.model.IngredienteResponse;

@FeignClient("INGREDIENTE-SERVICE")
public interface IngredienteClient {
    @GetMapping("/ingrediente/receita/{id}")
    List<IngredienteResponse> getIngredientesByReceitaId(@PathVariable Long id);
}
