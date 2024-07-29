package br.edu.infnet.ReceitaFacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.ReceitaFacil.model.Usuario;


@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    List<Usuario> findByUid(String uid);
}
