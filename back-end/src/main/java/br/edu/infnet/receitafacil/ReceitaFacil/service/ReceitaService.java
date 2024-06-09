package br.edu.infnet.receitafacil.ReceitaFacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.receitafacil.ReceitaFacil.model.Receita;
import br.edu.infnet.receitafacil.ReceitaFacil.repository.ReceitaRepository;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;

    public List<Receita> getReceitas(){
        return receitaRepository.findAll();
    }

    public Optional<Receita> getById(Long id){
        return receitaRepository.findById(id);
    }

    public List<Receita> getByUsuario(String uid){
        return receitaRepository.findByUsuario(uid);
    }

    public void add(Receita receita){
        receitaRepository.save(receita);
    }

    public Boolean update(Long id, Receita receita){
        return receitaRepository.findById(id).map(update -> {
            update.setReceita(receita);
            receitaRepository.save(update);
            return true;
        }).orElseGet(() -> {
            return false;
        });
    }

    public Boolean delete(Long id){
        if(receitaRepository.findById(id).isEmpty()) return false;
        receitaRepository.deleteById(id);
        return true;
    }    
}
