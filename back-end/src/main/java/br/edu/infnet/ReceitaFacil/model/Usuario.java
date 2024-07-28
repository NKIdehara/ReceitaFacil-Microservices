package br.edu.infnet.ReceitaFacil.model;

public class Usuario {
    private Long id;
    private String uid;
    private String email;

    public Usuario() {
    }

    public Usuario(Long id, String uid, String email) {
        this.id = id;
        this.uid = uid;
        this.email = email;
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
}