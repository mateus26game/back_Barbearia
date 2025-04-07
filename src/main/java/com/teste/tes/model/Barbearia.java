package com.teste.tes.model;

import com.teste.tes.Enum.StatusPagamento;
import com.teste.tes.Enum.StatusServico;
import com.teste.tes.Enum.TipoServico;
import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.LocalDate;

@Entity
@Table(name = "barbearias")
public class Barbearia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate diaParaCorte;

    @Column(nullable = false)
    private LocalTime inicioDoCorte;

    @Enumerated(EnumType.STRING) // Usa o nome do enum como string
    @Column(nullable = false)
    private StatusServico statusServico;

    @Enumerated(EnumType.STRING) // Usa o nome do enum como string
    @Column(nullable = false)
    private StatusPagamento statusPagamento;

    @Enumerated(EnumType.STRING) // Usa o nome do enum como string
    @Column(nullable = false)
    private TipoServico tipoServico;

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDiaParaCorte() {
        return diaParaCorte;
    }

    public void setDiaParaCorte(LocalDate diaParaCorte) {
        this.diaParaCorte = diaParaCorte;
    }

    public LocalTime getInicioDoCorte() {
        return inicioDoCorte;
    }

    public void setInicioDoCorte(LocalTime inicioDoCorte) {
        this.inicioDoCorte = inicioDoCorte;
    }

    public StatusServico getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(StatusServico statusServico) {
        this.statusServico = statusServico;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
