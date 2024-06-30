package br.edu.infnet.ReceitaFacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.ReceitaFacil.model.Medida;

@Repository
public interface MedidaRepository extends JpaRepository<Medida, Long> {
}
