package com.teste.tes.repository;

import com.teste.tes.Enum.StatusPagamento;
import com.teste.tes.Enum.StatusServico;
import com.teste.tes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByStatusPagamento(StatusPagamento statusPagamento);

    List<Cliente> findByStatusServico(StatusServico statusServico);

    List<Cliente> findByDiaParaCorte(LocalDate diaParaCorte);

}
