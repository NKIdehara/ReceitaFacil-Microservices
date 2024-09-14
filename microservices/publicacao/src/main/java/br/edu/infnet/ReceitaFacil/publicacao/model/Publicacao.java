package br.edu.infnet.ReceitaFacil.publicacao.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
public class Publicacao implements Serializable {
    private UUID uuid;
    private Long receitaId;
    private Status status;
}