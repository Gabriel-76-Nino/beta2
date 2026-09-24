package com.techfix.beta2.domain.ordem_servico.dto;

public record AtribuirDiagnostico(
        Long id,
        String diagnosticoTecnico,
        Boolean diagnosticoIgualRelato
) {
}
