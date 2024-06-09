package br.edu.infnet.receitafacil.ReceitaFacil.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.receitafacil.ReceitaFacil.model.Receita;
import br.edu.infnet.receitafacil.ReceitaFacil.service.ReceitaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
public class ReceitaController {
    @Autowired
    private ReceitaService receitaService;
    
    @GetMapping("/total")
    public int getReceitasCount(){
        return receitaService.getReceitas().size();
    }

    @GetMapping("/receita")
    public List<Receita> getReceitas(){
        return receitaService.getReceitas();
    }

    @GetMapping("/receita/{id}")
    public Optional<Receita> getById(@PathVariable Long id){
        return receitaService.getById(id);
    }

    @GetMapping("/receita/usuario/{uid}")
    public List<Receita> getByUsuario(@PathVariable String uid){
        return receitaService.getByUsuario(uid);
    }

    @PostMapping("/receita")
    public void add(@RequestBody Receita receita){
        receitaService.add(receita);
    }
    
    @PutMapping("receita/{id}")
    public void update(@PathVariable Long id, @RequestBody Receita receita) {
        receitaService.update(id, receita);
    }
    
    @DeleteMapping("/receita/{id}")
    public void delete(@PathVariable Long id){
        receitaService.delete(id);
    }
}