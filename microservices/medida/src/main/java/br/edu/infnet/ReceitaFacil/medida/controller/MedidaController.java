package br.edu.infnet.ReceitaFacil.medida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.ReceitaFacil.medida.service.MedidaService;


@RestController
@CrossOrigin
public class MedidaController {
    @Autowired
    private MedidaService medidaService;

    @GetMapping("/medida")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(medidaService.getAll());
    }

    @GetMapping("/medida/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(medidaService.getById(id));
    }
}
