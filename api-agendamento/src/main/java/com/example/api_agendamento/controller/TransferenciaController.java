package com.example.api_agendamento.controller;

import com.example.api_agendamento.model.Transferencia;
import com.example.api_agendamento.repository.TransferenciaRepository;
import com.example.api_agendamento.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<String> agendarTransferencia(@RequestBody Transferencia transferencia) {
        LocalDate hoje = LocalDate.now();

        if (transferencia.getDataTransferencia().isBefore(hoje)) {
            return ResponseEntity.badRequest().body("Data de transferência inválida.");
        }

        try {
            Double taxa = transferenciaService.calcularTaxa(
                    transferencia.getValor(),
                    transferencia.getDataTransferencia(),
                    hoje
            );
            transferencia.setTaxa(taxa);
            transferencia.setDataAgendamento(hoje);

            transferenciaRepository.save(transferencia);
            return ResponseEntity.ok("Transferência agendada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Transferencia> listarTransferencias() {
        return transferenciaRepository.findAll();
    }
}
