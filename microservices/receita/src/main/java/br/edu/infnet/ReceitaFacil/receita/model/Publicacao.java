package br.edu.infnet.ReceitaFacil.receita.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tblPublicacao")
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
public class Publicacao {
    @Id
    private UUID uuid;
    private Long receitaId;
    private Status status;
}