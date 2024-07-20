package br.edu.infnet.ReceitaFacil.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.edu.infnet.ReceitaFacil.model.Unidade.TipoUnidade;
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
    private String nomeMedida;
    private TipoUnidade tipo;

    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "medida", cascade = CascadeType.ALL)
    // @OneToMany(mappedBy = "medida")
    @OneToMany
    private List<Item> items;

    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "medida", cascade = CascadeType.ALL)
    // @OneToMany(mappedBy = "medida")
    @OneToMany
    @JsonManagedReference
    private List<Ingrediente> ingredientes;

    public Medida() {
    }

    public Medida(Long idMedida, String nomeMedida, TipoUnidade tipo) {
        this.id = idMedida;
        this.nomeMedida = nomeMedida;
        this.tipo = tipo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long idMedida) {
        this.id = idMedida;
    }

    public String getNomeMedida() {
        return this.nomeMedida;
    }

    public void setNomeMedida(String nomeMedida) {
        this.nomeMedida = nomeMedida;
    }

    public TipoUnidade getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoUnidade tipo) {
        this.tipo = tipo;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Ingrediente> getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public float convert(Medida medida) {
        if(this.nomeMedida == medida.getNomeMedida()) return 1.0f;
        return 0.0f;
    }
}