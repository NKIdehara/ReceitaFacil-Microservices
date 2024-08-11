package br.edu.infnet.ReceitaFacil.receita.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
public class ReceitaResponse {
    private Long id;
    private String usuario;
    private String nome;
    private String preparo;
    private String figura;
    private List<IngredienteResponse> ingredientes = new ArrayList<>();
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
    private float custo;
}