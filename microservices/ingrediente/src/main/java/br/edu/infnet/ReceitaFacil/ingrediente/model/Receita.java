package br.edu.infnet.ReceitaFacil.ingrediente.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class Receita {
    private Long id;
    private String usuario;
    private String nome;
    private String preparo;
    private String figura;
    private Status status;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
}