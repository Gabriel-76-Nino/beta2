package com.techfix.beta2.domain.ordemservico.dto;

public record AtribuirDiagnostico(
        Long id,
        String diagnosticoTecnico,
        Boolean diagnosticoIgualRelato
) {
}
