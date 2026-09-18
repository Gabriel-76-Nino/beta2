package com.techfix.beta2.domain.produto;

public record GarantiaDto(
        Long id,
        String fornecedor,
        String detalhesGarantia,
        Integer tempoGarantiaDias
) {
}
