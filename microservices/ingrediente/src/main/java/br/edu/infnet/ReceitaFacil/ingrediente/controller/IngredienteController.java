package br.edu.infnet.ReceitaFacil.ingrediente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.ReceitaFacil.ingrediente.model.IngredienteResponse;
import br.edu.infnet.ReceitaFacil.ingrediente.service.IngredienteService;

@RestController
@CrossOrigin
public class IngredienteController {
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/ingrediente")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ingredienteService.getAll());
    }

    @GetMapping("/ingrediente/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ingredienteService.getById(id));
    }

    @GetMapping("/ingrediente/receita/{id}")
    public ResponseEntity<?> getByReceitaId(@PathVariable Long id) {
        return ResponseEntity.ok(ingredienteService.getByReceitaId(id));
    }

    @PostMapping("/ingrediente/receita/{id}")
    public ResponseEntity<?> add(@PathVariable Long id, @RequestBody IngredienteResponse ingrediente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.addByReceitaId(id, ingrediente));
    }

    @PutMapping("ingrediente/{id}/receita/{receita_id}")
    public ResponseEntity<?> update(@PathVariable Long id, @PathVariable Long receita_id, @RequestBody IngredienteResponse ingrediente) {
        Boolean status = ingredienteService.update(receita_id, id, ingrediente);
        if(!status)
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Ingrediente não econtrado!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
    }

    @DeleteMapping("/ingrediente/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Boolean status = ingredienteService.delete(id);
        if(!status)
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Ingrediente não econtrado!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
    }
}