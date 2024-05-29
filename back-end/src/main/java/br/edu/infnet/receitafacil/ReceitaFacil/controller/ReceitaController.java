package br.edu.infnet.receitafacil.ReceitaFacil.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.receitafacil.ReceitaFacil.model.Receita;
import br.edu.infnet.receitafacil.ReceitaFacil.repository.ReceitaRepository;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ReceitaController {
    private ReceitaRepository receitas = new ReceitaRepository();
    
    @GetMapping("/receita")
    public List<Receita> getgetAll(){
        return receitas.getAll();
    }

    @GetMapping("/receita/{id}")
    public Receita getById(@PathVariable int id){
        return receitas.getById(id);
    }

    @PostMapping("/receita")
    public void add(@RequestBody Receita receita){
        receitas.add(receita);
    }
    
    @PutMapping("receita/{id}")
    public void update(@PathVariable int id, @RequestBody Receita receita) {
        receitas.update(id, receita);
    }
    
    @DeleteMapping("/receita/{id}")
    public void delete(@PathVariable int id){
        receitas.delete(id);
    }    
}
