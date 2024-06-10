package br.edu.infnet.receitafacil.ReceitaFacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.receitafacil.ReceitaFacil.model.Ingrediente;
import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    List<Ingrediente> findByReceitaId(Long id);    
}