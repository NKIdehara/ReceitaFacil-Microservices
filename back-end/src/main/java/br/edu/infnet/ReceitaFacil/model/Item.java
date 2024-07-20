package br.edu.infnet.ReceitaFacil.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblItems")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String descricao;
    private float preco;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "medida_id", referencedColumnName = "id")
    // @JsonBackReference
    private Medida medida;

    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    // @OneToMany(mappedBy = "item")
    @OneToMany
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public Item() {
    }

    public Item(Long idItem, String descricao, float preco, Medida medida) {
        this.id = idItem;
        this.descricao = descricao;
        this.preco = preco;
        this.medida = medida;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long idItem) {
        this.id = idItem;
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

    public List<Ingrediente> getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
