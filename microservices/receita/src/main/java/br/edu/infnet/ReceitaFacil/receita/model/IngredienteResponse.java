package br.edu.infnet.ReceitaFacil.receita.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
public class IngredienteResponse {
    private Long id;
    private ItemResponse item;
    private float quantidade;
    private Medida medida;
    private float custo;
}