package com.teste.tes.service;

import com.teste.tes.entity.Cliente;
import com.teste.tes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarCliente() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente com ID " + id + " não encontrada."));
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente existente = buscarClientePorId(id);
        existente.setNome(cliente.getNome());
        existente.setDiaParaCorte(cliente.getDiaParaCorte());
        existente.setInicioDoCorte(cliente.getInicioDoCorte());
        existente.setStatusServico(cliente.getStatusServico());
        existente.setStatusPagamento(cliente.getStatusPagamento());
        existente.setTipoServico(cliente.getTipoServico());
        existente.setPrecoBarbearia(cliente.getPrecoBarbearia());
        return clienteRepository.save(existente);
    }

    public void remover(Long id) {
        Cliente cliente = buscarClientePorId(id);
        clienteRepository.delete(cliente);
    }
}
