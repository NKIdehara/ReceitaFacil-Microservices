package br.edu.infnet.ReceitaFacil.receita.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.ReceitaFacil.receita.model.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, UUID> {
    Publicacao findByReceitaId(Long id);
}
