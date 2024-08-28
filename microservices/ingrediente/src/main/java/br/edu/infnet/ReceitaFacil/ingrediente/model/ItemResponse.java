package br.edu.infnet.ReceitaFacil.ingrediente.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
public class ItemResponse {
    private Long id;
    private String descricao;
    private float preco;
    private Medida medida;
}