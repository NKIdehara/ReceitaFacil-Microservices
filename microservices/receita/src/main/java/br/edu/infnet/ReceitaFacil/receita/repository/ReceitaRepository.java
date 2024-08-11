package br.edu.infnet.ReceitaFacil.receita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.ReceitaFacil.receita.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByUsuario(String usuario);
}