package br.edu.infnet.ReceitaFacil.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.ReceitaFacil.model.Ingrediente;
import br.edu.infnet.ReceitaFacil.service.IngredienteService;

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

    @GetMapping("/ingrediente/{idIngrediente}")
    public ResponseEntity<?> getById(@PathVariable Long idIngrediente){
        return ResponseEntity.ok(ingredienteService.getById(idIngrediente));
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

    @PutMapping("ingrediente/{idIngrediente}")
    public ResponseEntity<?> update(@PathVariable Long idIngrediente, @RequestBody Ingrediente ingrediente) {
        return ResponseEntity.ok(ingredienteService.update(idIngrediente, ingrediente));
    }
    @PutMapping("ingrediente/{idIngrediente}/receita/{idReceita}")
    public ResponseEntity<?> update(@PathVariable Long idIngrediente, @PathVariable Long idReceita, @RequestBody Ingrediente ingrediente) {
        return ResponseEntity.ok(ingredienteService.update(idReceita, idIngrediente, ingrediente));
    }
    
    @DeleteMapping("/ingrediente/{idIngrediente}")
    public ResponseEntity<?> delete(@PathVariable Long idIngrediente){
        return ResponseEntity.ok(ingredienteService.delete(idIngrediente));
    }
}