package br.edu.infnet.ReceitaFacil.ingrediente.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class Item {
    private Long id;
    private String descricao;
    private float preco;
    private Long medidaId;
}