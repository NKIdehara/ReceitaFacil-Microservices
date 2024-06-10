package br.edu.infnet.receitafacil.ReceitaFacil.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tblReceita")
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String nome;
    private String preparo;
    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ingrediente> ingredientes = new ArrayList<>();
    private Date data_receita;
    private String figura;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public Receita() {
    }

    public Receita(String usuario, String nome, String preparo, List<Ingrediente> ingredientes, Date data_receita, String figura) {
        this.usuario = usuario;
        this.nome = nome;
        this.preparo = preparo;
        this.data_receita = data_receita;
        this.figura = figura;
        this.ingredientes = ingredientes;
    }

    public Receita(Long id, String usuario, String nome, String preparo, List<Ingrediente> ingredientes, Date data_receita, String figura) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.preparo = preparo;
        this.data_receita = data_receita;
        this.figura = figura;
        this.ingredientes = ingredientes;
    }

    public void setReceita(Receita receita) {
        this.usuario = receita.usuario;
        this.nome = receita.nome;
        this.preparo = receita.preparo;
        this.ingredientes = receita.ingredientes;
        this.data_receita = receita.data_receita;
        this.figura = receita.figura;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreparo() {
        return this.preparo;
    }

    public void setPreparo(String preparo) {
        this.preparo = preparo;
    }

    public List<Ingrediente> getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Date getData_receita() {
        return this.data_receita;
    }

    public void setData_receita(Date data_receita) {
        this.data_receita = data_receita;
    }

    public String getFigura() {
        return this.figura;
    }

    public void setFigura(String figura) {
        this.figura = figura;
    }
}