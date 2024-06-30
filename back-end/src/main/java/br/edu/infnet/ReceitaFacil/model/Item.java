package br.edu.infnet.ReceitaFacil.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblItems")
public class Item {
    @Id
    private Long idItem;
    private String descricao;
    private float preco;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMedida")
    @JsonBackReference
    private Medida medida;

    public Item() {
    }

    public Item(Long idItem, String descricao, float preco, Medida medida) {
        this.idItem = idItem;
        this.descricao = descricao;
        this.preco = preco;
        this.medida = medida;
    }

    public Long getIdItem() {
        return this.idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return this.preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Medida getMedida() {
        return this.medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }
}
