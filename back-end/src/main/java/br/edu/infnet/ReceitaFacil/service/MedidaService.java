package br.edu.infnet.ReceitaFacil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.ReceitaFacil.model.Medida;
import br.edu.infnet.ReceitaFacil.model.Unidade.TipoUnidade;
import br.edu.infnet.ReceitaFacil.repository.MedidaRepository;

@Service
public class MedidaService {
    @Autowired
    private MedidaRepository medidaRepository;

    public List<Medida> getAll() {
        return medidaRepository.findAll();
    }

    public Medida getById(Long id) {
        return medidaRepository.findById(id).orElse(null);
    }

    public String getNome(Long id) {
        Medida medida = medidaRepository.findById(id).orElse(null);
        return medida.getNome();
    }

    public TipoUnidade getTipoUnidade(Long id) {
        Medida medida = medidaRepository.findById(id).orElse(null);
        return medida.getTipo();
    }
}
