package br.edu.infnet.ReceitaFacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.ReceitaFacil.model.Medida;
import br.edu.infnet.ReceitaFacil.repository.MedidaRepository;

@Service
public class MedidaService {
    @Autowired
    private MedidaRepository medidaRepository;

    public String getNome(Long id) {
        Medida medida = medidaRepository.findById(id).orElse(null);
        return medida.getNomeMedida();
    }
}
