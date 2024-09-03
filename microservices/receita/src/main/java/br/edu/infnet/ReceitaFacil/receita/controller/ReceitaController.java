package br.edu.infnet.ReceitaFacil.receita.controller;

import org.springframework.amqp.AmqpException;
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

import com.fasterxml.jackson.core.JsonProcessingException;

import br.edu.infnet.ReceitaFacil.receita.model.Receita;
import br.edu.infnet.ReceitaFacil.receita.service.ReceitaService;
import lombok.extern.slf4j.Slf4j;


@RestController
@CrossOrigin
@Slf4j
public class ReceitaController {
    @Autowired
    private ReceitaService receitaService;
    
    @GetMapping("/receita/total")
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
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(receitaService.add(receita));
        } catch (JsonProcessingException | AmqpException e) {
            log.error("Erro ao publicar receita", e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PutMapping("receita/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Receita receita) {
        Boolean status = receitaService.update(id, receita);
        if(!status)
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Receita não econtrada!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
    }
    
    @DeleteMapping("/receita/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Boolean status = receitaService.delete(id);
        if(!status)
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Receita não econtrada!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
    }
}