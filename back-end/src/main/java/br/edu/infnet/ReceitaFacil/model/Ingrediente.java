package br.edu.infnet.ReceitaFacil.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblIngredientes")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private float quantidade;

    @ManyToOne
    @JoinColumn(name = "medida_id")
    private Medida medida;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    @JsonBackReference
    private Receita receita;

    public Ingrediente() {
    }

    public Ingrediente(Item item, Float quantidade, Medida medida) {
        this.item = item;
        this.quantidade = quantidade;
        this.medida = medida;
    }

    public Ingrediente(Long id, Item item, Float quantidade, Medida medida, Receita receita) {
        this.id = id;
        this.item = item;
        this.quantidade = quantidade;
        this.medida = medida;
        this.receita = receita;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public float getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public Medida getMedida() {
        return this.medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    public Receita getReceita() {
        return this.receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public float getCusto() {
        return this.medida.convert(this.item.getMedida()) * this.quantidade * this.item.getPreco();
    }
}