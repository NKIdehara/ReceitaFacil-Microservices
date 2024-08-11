package br.edu.infnet.ReceitaFacil.usuario.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.ReceitaFacil.usuario.service.UsuarioService;

@RestController
@CrossOrigin
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario")
    public ResponseEntity<?> getAll() throws InterruptedException, ExecutionException{
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws InterruptedException, ExecutionException{
        Boolean status = usuarioService.delete(id);
        if(!status)
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Usuário não econtrado!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
    }
}
