package br.edu.infnet.receitafacil.ReceitaFacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.receitafacil.ReceitaFacil.model.Receita;
import java.util.List;


@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByUsuario(String usuario);
}
