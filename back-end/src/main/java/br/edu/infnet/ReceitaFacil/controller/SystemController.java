package br.edu.infnet.ReceitaFacil.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class SystemController {
    @GetMapping("/")
    public String getConnetion() {
        return "Conexão OK!";
    }
    
}
