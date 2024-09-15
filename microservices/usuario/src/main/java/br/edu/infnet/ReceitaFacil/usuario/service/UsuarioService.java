package br.edu.infnet.ReceitaFacil.usuario.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;

import br.edu.infnet.ReceitaFacil.usuario.model.Acesso;
import br.edu.infnet.ReceitaFacil.usuario.model.Usuario;
import br.edu.infnet.ReceitaFacil.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // @PostConstruct
    public void sync() throws InterruptedException, ExecutionException {
        System.out.println("Firebase sync init...");
        ApiFuture<ListUsersPage> query = FirebaseAuth.getInstance().listUsersAsync(null);
        ListUsersPage listUsersPage = query.get();
        log.info("Usuarios:" + listUsersPage.getValues().toString());
        for (ExportedUserRecord user : listUsersPage.getValues()) {
            if(usuarioRepository.findByUid(user.getUid()).size() == 0) {
                Usuario usuario;
                usuario = new Usuario(null, user.getUid(), user.getEmail(), Acesso.ADMIN);
                usuarioRepository.save(usuario);
                usuarioRepository.flush();
            }
        }
        
        System.out.println("Firebase sync completed...");
    }

    public List<Usuario> getAll() throws InterruptedException, ExecutionException {
        this.sync();
        return usuarioRepository.findAll();
    }
    
    public Boolean delete(Long id) throws InterruptedException, ExecutionException {
        if(usuarioRepository.findById(id).isEmpty()) return false;

        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        try {
            FirebaseAuth.getInstance().deleteUser(usuario.getUid());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return false;
        }
        usuarioRepository.deleteById(id);
        this.sync();
        return true;
    }
}
