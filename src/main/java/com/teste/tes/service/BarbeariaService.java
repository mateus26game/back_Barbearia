package com.teste.tes.service;

import com.teste.tes.model.Barbearia;
import com.teste.tes.repository.BarbeariaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarbeariaService {

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    public List<Barbearia> listarBarbearias() {
        return barbeariaRepository.findAll();
    }

    public Barbearia buscarBarbeariaPorId(Long id) {
        return barbeariaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Barbearia com ID " + id + " não encontrada."));
    }

    public Barbearia salvar(Barbearia barbearia) {
        return barbeariaRepository.save(barbearia);
    }

    public Barbearia atualizar(Long id, Barbearia barbearia) {
        Barbearia existente = buscarBarbeariaPorId(id);
        existente.setNome(barbearia.getNome());
        existente.setDiaParaCorte(barbearia.getDiaParaCorte());
        existente.setInicioDoCorte(barbearia.getInicioDoCorte());
        existente.setStatusServico(barbearia.getStatusServico());
        existente.setStatusPagamento(barbearia.getStatusPagamento());
        existente.setTipoServico(barbearia.getTipoServico());
        return barbeariaRepository.save(existente);
    }


    public void remover(Long id) {
        Barbearia barbearia = buscarBarbeariaPorId(id);
        barbeariaRepository.delete(barbearia);
    }
}
