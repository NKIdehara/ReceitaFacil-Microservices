package br.edu.infnet.receitafacil.ReceitaFacil.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.receitafacil.ReceitaFacil.model.Receita;
import br.edu.infnet.receitafacil.ReceitaFacil.service.ReceitaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class ReceitaController {
    @Autowired
    private ReceitaService receitaService;
    
    @GetMapping("/total")
    public ResponseEntity<?> getCount(){
        return ResponseEntity.ok(receitaService.getAll().size());
    }

    @GetMapping("/receita")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(receitaService.getAll());
    }

    @GetMapping("/receita/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(receitaService.getById(id));
    }

    @GetMapping("/receita/usuario/{uid}")
    public ResponseEntity<?> getByUsuarioId(@PathVariable String uid){
        return ResponseEntity.ok(receitaService.getByUsuarioId(uid));
    }

    @PostMapping("/receita")
    public ResponseEntity<?> add(@RequestBody Receita receita){
        return ResponseEntity.status(HttpStatus.CREATED).body(receitaService.add(receita));
    }
    
    @PutMapping("receita/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Receita receita) {
        if(!receitaService.update(id, receita))
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(0);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(1);
    }
    
    @DeleteMapping("/receita/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!receitaService.delete(id))
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(0);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(1);
    }
}