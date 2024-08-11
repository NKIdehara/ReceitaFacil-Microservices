package br.edu.infnet.ReceitaFacil.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.ReceitaFacil.usuario.model.Usuario;


@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    List<Usuario> findByUid(String uid);
}
