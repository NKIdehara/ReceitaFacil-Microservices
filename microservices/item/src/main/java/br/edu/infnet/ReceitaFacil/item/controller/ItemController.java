package br.edu.infnet.ReceitaFacil.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.ReceitaFacil.item.model.ItemResponse;
import br.edu.infnet.ReceitaFacil.item.service.ItemService;

@RestController
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(itemService.getAll());
    }
    @GetMapping("/item/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getById(id));
    }

    @PostMapping("/item/")
    public ResponseEntity<?> add(@RequestBody ItemResponse item){
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.add(item));
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Boolean status = itemService.delete(id);
        if(!status)
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Item não econtrado!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
    }
}
