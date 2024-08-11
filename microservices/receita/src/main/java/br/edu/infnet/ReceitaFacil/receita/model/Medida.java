package br.edu.infnet.ReceitaFacil.receita.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class Medida {
    private Long id;
    private String nome;
    private Unidade unidade;

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