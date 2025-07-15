
package com.teste.tes.controller;

import com.teste.tes.entity.Cliente;
import com.teste.tes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/barbearias")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(
            summary = "Listar todos os clientes",
            description = "Método que retorna todos os registros de clientes cadastrados no banco de dados.",
            tags = {"Cliente"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/listar")
    public List<Cliente> listar() {
        return clienteService.listarCliente();
    }

    @Operation(
            summary = "Buscar cliente por ID",
            description = "Método que retorna os dados de um cliente com base no ID informado.",
            tags = {"Cliente"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @Operation(
            summary = "Adicionar novo cliente",
            description = "Método que adiciona os dados de um novo cliente ao banco de dados.",
            tags = {"Cliente"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/adicionar")
    public ResponseEntity<Cliente> adicionar(@RequestBody Cliente cliente) {
        Cliente salvo = clienteService.salvar(cliente);
        return ResponseEntity.status(201).body(salvo);
    }

    @Operation(
            summary = "Atualizar cliente por ID",
            description = "Método que atualiza os dados de um cliente com base no ID informado.",
            tags = {"Cliente"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente atualizado = clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(
            summary = "Deletar cliente por ID",
            description = "Método que remove um cliente do banco de dados com base no ID informado.",
            tags = {"Cliente"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.remover(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Listar clientes filtrando por status de pagamento",
            description = "Retorna clientes que já pagaram ou não pagaram, baseado no valor passado",
            tags = {"Cliente"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista filtrada retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetro inválido")
    })
    @GetMapping("/filtrar")
    public ResponseEntity<List<Cliente>> listarPorStatus(@RequestParam(required = false) String statusPagamento) {
        List<Cliente> clientes = clienteService.listarPorStatusPagamento(statusPagamento);
        return ResponseEntity.ok(clientes);
    }

    @Operation(
            summary = "Listar clientes filtrando por status de serviço",
            description = "Retorna clientes com serviço FINALIZADO ou ESPERADO",
            tags = {"Cliente"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista filtrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetro inválido")
    })
    @GetMapping("/filtrar-status-servico")
    public ResponseEntity<List<Cliente>> listarPorStatusServico(@RequestParam(required = false) String statusServico) {
        List<Cliente> clientes = clienteService.listarPorStatusServico(statusServico);
        return ResponseEntity.ok(clientes);
    }


    @GetMapping("/nota-fiscal/{id}")
    public ResponseEntity<byte[]> gerarNota(@PathVariable Long id) throws IOException {
        byte[] pdf = clienteService.gerarNotaFiscal(id);

        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=nota_fiscal_" + id + ".pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdf);
    }


        @GetMapping("/Todas-nota-fiscal")
        public ResponseEntity<byte[]> gerarTodasNota() throws IOException {
            byte[] pdf = clienteService.gerarTodasNotaFiscal();

            return ResponseEntity.ok()
                    .header("Content-Disposition", "inline; filename=notas_fiscais.pdf")
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(pdf);
        }


    @GetMapping("/cortes-hoje")
    public ResponseEntity<List<Cliente>> listarCortesDeHoje() {
        List<Cliente> clientes = clienteService.listarCortesDeHoje();
        return ResponseEntity.ok(clientes);
    }



}
