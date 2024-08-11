package br.edu.infnet.ReceitaFacil.ingrediente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.ReceitaFacil.ingrediente.model.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    List<Ingrediente> findByReceitaId(Long id);
}