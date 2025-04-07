package com.teste.tes.controller;

import com.teste.tes.model.Barbearia;
import com.teste.tes.service.BarbeariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barbearias")
public class BarbeariaController {

    private BarbeariaService barbeariaService;

    public BarbeariaController(BarbeariaService barbeariaService) {
        this.barbeariaService = barbeariaService;
    }

    @GetMapping("/listar")
    public List<Barbearia> listar() {
        return barbeariaService.listarBarbearias();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Barbearia> buscar(@PathVariable Long id) {
        Barbearia barbearia = barbeariaService.buscarBarbeariaPorId(id);
        return ResponseEntity.ok(barbearia);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Barbearia> adicionar(@RequestBody Barbearia barbearia) {
        Barbearia salvo = barbeariaService.salvar(barbearia);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Barbearia> atualizar(@PathVariable Long id, @RequestBody Barbearia barbearia) {
        Barbearia atualizado = barbeariaService.atualizar(id, barbearia);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        barbeariaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
