package br.edu.infnet.ReceitaFacil.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.ReceitaFacil.model.Usuario;

@RestController
@CrossOrigin
public class UsuarioController {
    private List<Usuario> usuarios = new ArrayList<>();

    @GetMapping("/usuario")
    public ResponseEntity<?> getAll(){
        if(usuarios.isEmpty()){
            usuarios.add(new Usuario(1L, "uid 1", "Usuario 1"));
            usuarios.add(new Usuario(2L, "uid 2", "Usuario 2"));
            usuarios.add(new Usuario(3L, "uid 3", "Usuario 3"));
        }
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        int count = usuarios.size();
        Usuario delUsuario = null;
        for(Usuario usuario : usuarios) {
            if(usuario.getId().equals(id)) delUsuario = usuario;
        }
        usuarios.remove(delUsuario);
        Boolean status = !(usuarios.size() == count);
        if(!status)
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Item não econtrado!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
    }
}
