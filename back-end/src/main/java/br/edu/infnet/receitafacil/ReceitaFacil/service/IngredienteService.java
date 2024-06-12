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

    public Optional<Ingrediente> getById(Long idIngrediente) {
        return ingredienteRepository.findById(idIngrediente);
    }

    public Long add(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente).getIdIngrediente();
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

    public Boolean update(Long idIngrediente, Ingrediente ingrediente) {
        return ingredienteRepository.findById(idIngrediente).map(update -> {
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

    public Boolean delete(Long idIngrediente) {
        if(ingredienteRepository.findById(idIngrediente).isEmpty()) return false;
        ingredienteRepository.deleteById(idIngrediente);
        return true;
    }
}
