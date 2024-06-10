package br.edu.infnet.receitafacil.ReceitaFacil.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.receitafacil.ReceitaFacil.model.Ingrediente;
import br.edu.infnet.receitafacil.ReceitaFacil.service.IngredienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
public class IngredienteController {
    @Autowired
    private IngredienteService ingredienteService;
    
    @GetMapping("/ingrediente")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(ingredienteService.getAll());
    }

    @GetMapping("/ingrediente/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(ingredienteService.getById(id));
    }

    @GetMapping("/ingrediente/receita/{id}")
    public ResponseEntity<?> getByReceitaId(@PathVariable Long id){
        return ResponseEntity.ok(ingredienteService.getByReceitaId(id));
    }

    @PostMapping("/ingrediente")
    public ResponseEntity<?> add(@RequestBody Ingrediente ingrediente){
        return ResponseEntity.ok(ingredienteService.add(ingrediente));
    }

    @PostMapping("/ingrediente/receita/{id}")
    public ResponseEntity<?> add(@PathVariable Long id, @RequestBody Ingrediente ingrediente){
        return ResponseEntity.ok(ingredienteService.addByReceitaId(id, ingrediente));
    }

    @PutMapping("ingrediente/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Ingrediente ingrediente) {
        return ResponseEntity.ok(ingredienteService.update(id, ingrediente));
    }
    
    @DeleteMapping("/ingrediente/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(ingredienteService.delete(id));
    }
}