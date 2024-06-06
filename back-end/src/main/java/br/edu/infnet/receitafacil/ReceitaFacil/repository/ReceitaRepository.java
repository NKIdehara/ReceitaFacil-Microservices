package br.edu.infnet.receitafacil.ReceitaFacil.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.infnet.receitafacil.ReceitaFacil.model.Receita;

public class ReceitaRepository {
    private static List<Receita> receitas;

    public ReceitaRepository() {
        if(receitas == null){
            receitas = new ArrayList<>();

            SimpleDateFormat style = new SimpleDateFormat("yyyy-MM-dd");
            String data_receita = style.format(new Date());

            // TESTES
            receitas.add(new Receita(0, "nOCEzyRcfXN3UqtSlfyrqzgsOKL2", "Arroz", "Lavar o arroz; colocar sal; cozinhar por 30 minutos.", "- 1 xícara de arroz\\n- 1/2 colher de sal", data_receita, "https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_1.png?alt=media&token=30015b83-a563-4bc8-8e90-8f92e40f19c3"));
            receitas.add(new Receita(1, "nOCEzyRcfXN3UqtSlfyrqzgsOKL2", "Carne", "Temperar a carne; assar até dourar.", "- 1 bife\\n- tempero a gosto", data_receita, "https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_2.png?alt=media&token=6ace9d66-e77c-420c-a85f-1d6841acb6f0"));
            receitas.add(new Receita(2, "nOCEzyRcfXN3UqtSlfyrqzgsOKL2", "Macarrão", "Cozinhar o macarrão; escorrer e colocar o molho.", "- 500g de macarrão\\n- 1 lata de molho de tomate", data_receita, "https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_3.png?alt=media&token=47038466-478b-423a-8e11-5c74f88f4d2a"));
        }
    }

    public List<Receita> getAll(){
        return receitas;
    }

    public Receita getById(int id){
        return receitas.stream().filter(receita -> receita.getId() == id).findFirst().orElse(null);
    }

    public List<Receita> getByUsuario(String uid){
        return receitas.stream().filter(receita -> receita.getUsuario().equals(uid)).collect(Collectors.toList());
    }

    public void add(Receita receita){
        receitas.add(receita);
    }

    public void update(int id, Receita receita){
        int i = 0;
        for(Receita r: receitas){
            if(id == r.getId()){
                receitas.set(i, receita);
                break;
            }
            i++;
        }
    }

    public void delete(int id){
        int i = 0;
        for(Receita r: receitas){
            if(id == r.getId()){
                receitas.remove(i);
                break;
            }
            i++;
        }
    }
}
