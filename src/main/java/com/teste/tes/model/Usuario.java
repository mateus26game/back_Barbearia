package com.teste.tes.model;

import jakarta.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date diaTrabalho;

    @Column(nullable = false)
    private LocalTime inicioTrabalho;

    @Column(nullable = false)
    private LocalTime fimExpediente;

    // Getters e Setters


    public LocalTime getInicioTrabalho() {
        return inicioTrabalho;
    }

    public void setInicioTrabalho(LocalTime inicioTrabalho) {
        this.inicioTrabalho = inicioTrabalho;
    }

    public LocalTime getFimExpediente() {
        return fimExpediente;
    }

    public void setFimExpediente(LocalTime fimExpediente) {
        this.fimExpediente = fimExpediente;
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

    public Date getDiaTrabalho() {
        return diaTrabalho;
    }

    public void setDiaTrabalho(Date diaTrabalho) {
        this.diaTrabalho = diaTrabalho;
    }

}