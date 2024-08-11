package br.edu.infnet.ReceitaFacil.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.ReceitaFacil.item.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
