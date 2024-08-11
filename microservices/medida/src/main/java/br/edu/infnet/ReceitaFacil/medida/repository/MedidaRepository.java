package br.edu.infnet.ReceitaFacil.medida.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.ReceitaFacil.medida.model.Medida;

@Repository
public interface MedidaRepository extends JpaRepository<Medida, Long> {
}
