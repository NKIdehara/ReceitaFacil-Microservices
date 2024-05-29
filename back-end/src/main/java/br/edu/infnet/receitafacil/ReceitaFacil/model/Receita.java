package br.edu.infnet.receitafacil.ReceitaFacil.model;

import java.util.Date;

public class Receita {
    private int id;
    private String usuario;
    private String nome;
    private String preparo;
    private String ingredientes;
    private Date data_receita;
    private String figura;

    public Receita() {
    }

    public Receita(int id, String usuario, String nome, String preparo, String ingredientes, Date data_receita, String figura) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.preparo = preparo;
        this.ingredientes = ingredientes;
        this.data_receita = data_receita;
        this.figura = figura;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public String getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(String ingredientes) {
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
