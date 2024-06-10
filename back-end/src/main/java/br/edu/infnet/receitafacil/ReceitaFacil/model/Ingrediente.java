package br.edu.infnet.receitafacil.ReceitaFacil.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tblIngrediente")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Float quantidade;
    private String medida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receita")
    @JsonBackReference
    private Receita receita;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public Ingrediente() {
    }

    public Ingrediente(String nome, Float quantidade, String medida) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.medida = medida;
    }

    public Ingrediente(Long id, String nome, Float quantidade, String medida, Receita receita) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.medida = medida;
        this.receita = receita;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.id = ingrediente.id;
        this.nome = ingrediente.nome;
        this.quantidade = ingrediente.quantidade;
        this.medida = ingrediente.medida;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public String getMedida() {
        return this.medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Receita getReceita() {
        return this.receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }    
}