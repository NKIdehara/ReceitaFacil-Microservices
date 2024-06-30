package br.edu.infnet.ReceitaFacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.ReceitaFacil.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
