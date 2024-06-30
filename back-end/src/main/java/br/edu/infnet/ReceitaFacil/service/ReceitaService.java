package br.edu.infnet.ReceitaFacil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.ReceitaFacil.model.Receita;
import br.edu.infnet.ReceitaFacil.repository.ReceitaRepository;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;

    public List<Receita> getAll() {
        // List<Receita> receitas = receitaRepository.findAll();
        // return receitas.stream().map(receita ->
        //     new Receita(
        //             receita.getId(),
        //             receita.getUsuario(),
        //             receita.getNome(),
        //             receita.getPreparo(),
        //             receita.getFigura(),
        //             receita.getCusto(),
        //             receita.getIngredientes(),
        //             receita.getCreatedDate(),
        //             receita.getCreatedBy(),
        //             receita.getLastModifiedDate(),
        //             receita.getLastModifiedBy()
        //         )).collect(Collectors.toList());
        return receitaRepository.findAll();
    }

    public Receita getById(Long id) {
        // Receita receita = receitaRepository.findById(id).orElse(null);
        // return new Receita(
        //     receita.getId(),
        //     receita.getUsuario(),
        //     receita.getNome(),
        //     receita.getPreparo(),
        //     receita.getFigura(),
        //     receita.getCusto(),
        //     receita.getIngredientes(),
        //     receita.getCreatedDate(),
        //     receita.getCreatedBy(),
        //     receita.getLastModifiedDate(),
        //     receita.getLastModifiedBy());
        return receitaRepository.findById(id).orElse(null);
    }

    public List<Receita> getByUsuarioId(String uid) {
        // List<Receita> receitas = receitaRepository.findByUsuario(uid);
        // return receitas.stream().map(receita ->
        //     new Receita(
        //             receita.getId(),
        //             receita.getUsuario(),
        //             receita.getNome(),
        //             receita.getPreparo(),
        //             receita.getFigura(),
        //             receita.getCusto(),
        //             receita.getIngredientes(),
        //             receita.getCreatedDate(),
        //             receita.getCreatedBy(),
        //             receita.getLastModifiedDate(),
        //             receita.getLastModifiedBy()
        //         )).collect(Collectors.toList());
        return receitaRepository.findByUsuario(uid);
    }

    public Long add(Receita receita) {
        return receitaRepository.save(receita).getId();
    }

    public Boolean update(Long id, Receita receita) {
        return receitaRepository.findById(id).map(update -> {
            update.setUsuario(receita.getUsuario());
            update.setNome(receita.getNome());
            update.setPreparo(receita.getPreparo());
            update.setIngredientes(receita.getIngredientes());
            update.setFigura(receita.getFigura());
            receitaRepository.save(update);
            return true;
        }).orElseGet(() -> {
            return false;
        });
    }

    public Boolean delete(Long id) {
        if(receitaRepository.findById(id).isEmpty()) return false;
        receitaRepository.deleteById(id);
        return true;
    }
}