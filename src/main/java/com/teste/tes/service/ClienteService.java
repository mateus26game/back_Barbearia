package com.teste.tes.service;

import com.itextpdf.io.font.constants.StandardFonts;
import java.io.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.teste.tes.Enum.StatusPagamento;
import com.teste.tes.Enum.StatusServico;
import com.teste.tes.entity.Cliente;
import com.teste.tes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetTime;
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

    public List<Cliente> listarPorStatusPagamento(String statusPagamento) {
        if (statusPagamento == null || statusPagamento.isBlank()) {
            return listarCliente();
        }

        try {
            StatusPagamento status = StatusPagamento.valueOf(statusPagamento.toUpperCase());
            return clienteRepository.findByStatusPagamento(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("StatusPagamento inválido. Use: PAGO ou NAO_PAGO");
        }
    }

    public List<Cliente> listarPorStatusServico(String statusServico) {
        if (statusServico == null || statusServico.isBlank()) {
            return listarCliente();
        }

        try {
            StatusServico status = StatusServico.valueOf(statusServico.toUpperCase());
            return clienteRepository.findByStatusServico(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("StatusServico inválido. Use: FINALIZADO ou ESPERADO");
        }
    }

    public List<Cliente> listarCortesDeHoje() {
        LocalDate hoje = LocalDate.now();
        return clienteRepository.findByDiaParaCorte(hoje);
    }









    public byte[] gerarNotaFiscal(Long id) throws IOException {
        Cliente cliente = buscarClientePorId(id);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Fonte em negrito
        PdfFont fontBold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        // Título centralizado e grande
        Paragraph titulo = new Paragraph("MY BARBEARIA")
                .setFont(fontBold)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(titulo);

        // Espaço
        document.add(new Paragraph("\n"));

        // Cabeçalho da nota
        document.add(new Paragraph("NOTA FISCAL").setFont(fontBold).setFontSize(14));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));

        // Informações do cliente
        document.add(new Paragraph("Cliente: " + cliente.getNome()));
        document.add(new Paragraph("Data do Corte: " + cliente.getDiaParaCorte()));
        document.add(new Paragraph("Horário: " + cliente.getInicioDoCorte()));
        document.add(new Paragraph("Serviço: " + cliente.getTipoServico()));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));

        document.add(new Paragraph("Status Pagamento: " + cliente.getStatusPagamento()));

        // Linha separadora
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));

        // Valor
        document.add(new Paragraph("Valor total: R$ " + cliente.getPrecoBarbearia()).setFont(fontBold));

        // Espaço final
        document.add(new Paragraph("\n\nObrigado pela preferência!"));

        document.close();
        return out.toByteArray();
    }


    public byte[] gerarTodasNotaFiscal() throws IOException {
        List<Cliente> clientes = clienteRepository.findAll();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        PdfFont fontBold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        // Título geral
        Paragraph tituloGeral = new Paragraph("RELATÓRIO DE NOTAS FISCAIS - MY BARBEARIA")
                .setFont(fontBold)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(tituloGeral);
        document.add(new Paragraph("\n\n"));

        for (Cliente cliente : clientes) {
            document.add(new Paragraph("NOTA FISCAL").setFont(fontBold).setFontSize(14));
            document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------"));

            document.add(new Paragraph("Cliente: " + cliente.getNome()));
            document.add(new Paragraph("Data do Corte: " + cliente.getDiaParaCorte()));
            document.add(new Paragraph("Horário: " + cliente.getInicioDoCorte()));
            document.add(new Paragraph("Serviço: " + cliente.getTipoServico()));
            document.add(new Paragraph("Status Pagamento: " + cliente.getStatusPagamento()));
            document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------"));

            document.add(new Paragraph("Valor total: R$ " + cliente.getPrecoBarbearia()).setFont(fontBold));

            document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph("\n"));
        }

        document.add(new Paragraph("Gerado automaticamente. Obrigado!").setTextAlignment(TextAlignment.CENTER));

        document.close();
        return out.toByteArray();
    }


}
