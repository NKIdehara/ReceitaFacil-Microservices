package br.edu.infnet.ReceitaFacil.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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

    @ManyToOne
    @JoinColumn(name = "medida_id", referencedColumnName = "id")
    private Medida medida;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public Item() {
    }

    public Item(Long id, String descricao, float preco, Medida medida) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.medida = medida;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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