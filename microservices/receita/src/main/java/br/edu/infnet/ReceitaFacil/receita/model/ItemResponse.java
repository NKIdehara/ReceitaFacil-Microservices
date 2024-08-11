package br.edu.infnet.ReceitaFacil.receita.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class ItemResponse {
    private Long id;
    private String descricao;
    private float preco;
    private Medida medida;
}