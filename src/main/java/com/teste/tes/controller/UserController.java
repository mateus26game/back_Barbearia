package com.teste.tes.controller;

import com.teste.tes.model.Usuario;
import com.teste.tes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> ResponseEntity.ok(usuario))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return ResponseEntity.status(201).body(usuarioSalvo);
    }

    @PutMapping("/time/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioAtualizado.getNome());
                    usuario.setDiaTrabalho(usuarioAtualizado.getDiaTrabalho());
                    usuario.setInicioTrabalho(usuarioAtualizado.getInicioTrabalho());
                    usuario.setFimExpediente(usuarioAtualizado.getFimExpediente());
                    Usuario usuarioSalvo = usuarioRepository.save(usuario);
                    return ResponseEntity.ok(usuarioSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuarioRepository.delete(usuario);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
