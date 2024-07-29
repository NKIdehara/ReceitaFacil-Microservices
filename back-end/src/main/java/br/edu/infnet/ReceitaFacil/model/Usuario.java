package br.edu.infnet.ReceitaFacil.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblUsuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String uid;
    private String email;
    private Acesso acesso;

    public Usuario() {
    }

    public Usuario(Long id, String uid, String email, Acesso acesso) {
        this.id = id;
        this.uid = uid;
        this.email = email;
        this.acesso = acesso;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Acesso getAcesso() {
        return this.acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }
}