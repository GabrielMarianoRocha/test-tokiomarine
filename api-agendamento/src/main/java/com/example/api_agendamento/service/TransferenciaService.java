package com.example.api_agendamento.service;

import com.example.api_agendamento.model.Transferencia;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class TransferenciaService {

    public Double calcularTaxa(Double valor, LocalDate dataTransferencia, LocalDate dataAgendamento) {
        long dias = ChronoUnit.DAYS.between(dataAgendamento, dataTransferencia);

        if (dias == 0) return 3.0 + (valor * 0.025);
        if (dias <= 10) return 12.0;
        if (dias <= 20) return valor * 0.082;
        if (dias <= 30) return valor * 0.069;
        if (dias <= 40) return valor * 0.047;
        if (dias <= 50) return valor * 0.017;

        throw new IllegalArgumentException("Dias fora do intervalo permitido.");
    }
}
