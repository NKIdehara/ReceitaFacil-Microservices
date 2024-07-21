package br.edu.infnet.ReceitaFacil.model;

import java.util.List;

import br.edu.infnet.ReceitaFacil.model.Unidade.TipoUnidade;
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
    private TipoUnidade tipo;

    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "medida", cascade = CascadeType.ALL)
    // @OneToMany(mappedBy = "medida")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "medida", cascade = CascadeType.ALL)
    // @OneToMany(mappedBy = "medida")
    @OneToMany(cascade = CascadeType.ALL)
    // @JsonManagedReference
    private List<Ingrediente> ingredientes;

    public Medida() {
    }

    public Medida(Long id, String nome, TipoUnidade tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
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

    public TipoUnidade getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoUnidade tipo) {
        this.tipo = tipo;
    }

    // public List<Item> getItems() {
    //     return this.items;
    // }

    // public void setItems(List<Item> items) {
    //     this.items = items;
    // }

    // public List<Ingrediente> getIngredientes() {
    //     return this.ingredientes;
    // }

    // public void setIngredientes(List<Ingrediente> ingredientes) {
    //     this.ingredientes = ingredientes;
    // }

    public float convert(Medida medida) {
        if(this.nome == medida.getNome()) return 1.0f;
        return 0.0f;
    }
}