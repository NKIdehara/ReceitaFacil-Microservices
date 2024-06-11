package br.edu.infnet.receitafacil.ReceitaFacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.receitafacil.ReceitaFacil.model.Ingrediente;
import br.edu.infnet.receitafacil.ReceitaFacil.repository.IngredienteRepository;
import br.edu.infnet.receitafacil.ReceitaFacil.repository.ReceitaRepository;

@Service
public class IngredienteService {
    @Autowired
    private ReceitaRepository receitaRepository;
    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<Ingrediente> getAll() {
        return ingredienteRepository.findAll();
    }

    public List<Ingrediente> getByReceitaId(Long id) {
        return ingredienteRepository.findByReceitaId(id);
    }

    public Optional<Ingrediente> getById(Long id) {
        return ingredienteRepository.findById(id);
    }

    public Long add(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente).getId();
    }

    public Boolean addByReceitaId(Long id, Ingrediente ingrediente) {
        return receitaRepository.findById(id).map(receita -> {
            ingrediente.setReceita(receita);
            ingredienteRepository.save(ingrediente);
            return true;
        }).orElseGet(() -> {
            return false;
        });
    }

    public Boolean update(Long id, Ingrediente ingrediente) {
        return ingredienteRepository.findById(id).map(update -> {
            update.setIngrediente(ingrediente);
            ingredienteRepository.save(update);
            return true;
        }).orElseGet(() -> {
            return false;
        });
    }
    public Boolean update(Long idReceita, Long idIngrediente, Ingrediente ingrediente) {
        return ingredienteRepository.findById(idIngrediente).map(update -> {
            update.setIngrediente(ingrediente);
            ingredienteRepository.save(update);
            return true;
        }).orElseGet(() -> {
            return receitaRepository.findById(idReceita).map(receita -> {
                ingrediente.setReceita(receita);
                ingredienteRepository.save(ingrediente);
                return true;
            }).orElseGet(() -> {
                return false;
            });
        });
    }

    public Boolean delete(Long id) {
        if(ingredienteRepository.findById(id).isEmpty()) return false;
        ingredienteRepository.deleteById(id);
        return true;
    }
}
