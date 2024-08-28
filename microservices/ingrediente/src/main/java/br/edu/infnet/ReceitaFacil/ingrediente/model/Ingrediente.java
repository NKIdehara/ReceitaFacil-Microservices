package br.edu.infnet.ReceitaFacil.ingrediente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tblIngredientes")
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private float quantidade;
    private Long itemId;
    private Long medidaId;
    private Long receitaId;
}