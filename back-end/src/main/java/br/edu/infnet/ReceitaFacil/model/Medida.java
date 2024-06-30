package br.edu.infnet.ReceitaFacil.model;

import br.edu.infnet.ReceitaFacil.model.Unidade.TipoUnidade;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblMedidas")
public class Medida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedida;
    private String nomeMedida;
    private TipoUnidade tipo;

    @OneToOne(mappedBy = "medida")
    private Item item;

    public Medida() {
    }

    public Medida(Long idMedida, String nomeMedida, TipoUnidade tipo) {
        this.idMedida = idMedida;
        this.nomeMedida = nomeMedida;
        this.tipo = tipo;
    }

    public Long getIdMedida() {
        return this.idMedida;
    }

    public void setIdMedida(Long idMedida) {
        this.idMedida = idMedida;
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

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
