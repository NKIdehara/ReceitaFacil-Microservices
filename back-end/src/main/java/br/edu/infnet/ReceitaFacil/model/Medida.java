package br.edu.infnet.ReceitaFacil.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblMedidas")
public class Medida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nome;
    private Unidade unidade;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingrediente> ingredientes;

    public Medida() {
    }

    public Medida(Long id, String nome, Unidade unidade) {
        this.id = id;
        this.nome = nome;
        this.unidade = unidade;
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

    public Unidade getUnidade() {
        return this.unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public float convert(Medida medida) {
        if(this.nome.equals(medida.getNome())) return 1.0f;
        if(this.nome.equals("g") && medida.getNome().equals("kg")) return 0.001f;
        if(this.nome.equals("kg") && medida.getNome().equals("g")) return 1000.0f;
        if(this.nome.equals("ml") && medida.getNome().equals("l")) return 0.001f;
        if(this.nome.equals("l") && medida.getNome().equals("ml")) return 1000.0f;
        if(this.nome.equals("l") && medida.getNome().equals("xícara(s)")) return 4.22675f;
        if(this.nome.equals("xícara(s)") && medida.getNome().equals("l")) return 1/4.22675f;
        if(this.nome.equals("l") && medida.getNome().equals("colher(es) de sopa")) return 67.628f;
        if(this.nome.equals("colher(es) de sopa") && medida.getNome().equals("l")) return 1/67.628f;
        if(this.nome.equals("l") && medida.getNome().equals("xícara(s)")) return 0.00422675f;
        if(this.nome.equals("xícara(s)") && medida.getNome().equals("ml")) return 1/0.00422675f;
        if(this.nome.equals("ml") && medida.getNome().equals("colher(es) de sopa")) return 0.067628f;
        if(this.nome.equals("colher(es) de sopa") && medida.getNome().equals("ml")) return 1/0.067628f;
        if(this.nome.equals("xícara(s)") && medida.getNome().equals("colher(es) de sopa")) return 16.0f;
        if(this.nome.equals("colher(es) de sopa") && medida.getNome().equals("xícara(s)")) return 1/16.0f;
        return 0.0f;
    }
}