package br.edu.infnet.ReceitaFacil.controller;

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

import br.edu.infnet.ReceitaFacil.model.Item;
import br.edu.infnet.ReceitaFacil.service.ItemService;
import br.edu.infnet.ReceitaFacil.service.MedidaService;

@RestController
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private MedidaService medidaService;

    @GetMapping("/item")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(itemService.getAll());
    }

    @PostMapping("/item/{medida_id}")
    public ResponseEntity<?> add(@RequestBody Item item, @PathVariable Long medida_id){
        item.setMedida(medidaService.getById(medida_id));
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