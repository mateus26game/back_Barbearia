package com.teste.tes.entity;

import com.teste.tes.Enum.StatusPagamento;
import com.teste.tes.Enum.StatusServico;
import com.teste.tes.Enum.TipoServico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.LocalDate;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da Cliente", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nome do cliente que será atendido na barbearia", example = "Pedro Santiago")
    private String nome;

    @Column(nullable = false)
    @Schema(description = "Data marcada para o corte de cabelo", example = "2009-09-10")
    private LocalDate diaParaCorte;

    @Column(nullable = false)
    @Schema(description = "Horário marcado para o início do corte", example = "09:10")
    private LocalTime inicioDoCorte;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Status do atendimento: se já foi atendido ou ainda será atendido", example = "FINALIZADO")
    private StatusServico statusServico;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Status do pagamento: se o cliente já pagou ou ainda vai pagar", example = "PAGOU")
    private StatusPagamento statusPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Tipo de serviço solicitado: cabelo, barba ou ambos", example = "CABELO")
    private TipoServico tipoServico;

    @Column(nullable = false)
    @Schema(description = "Preço do serviço", example = "30.00")
    private BigDecimal precoBarbearia;

    // Getters e Setters



    public BigDecimal getPrecoBarbearia() {
        return precoBarbearia;
    }

    public void setPrecoBarbearia(BigDecimal precoBarbearia) {
        this.precoBarbearia = precoBarbearia;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

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
