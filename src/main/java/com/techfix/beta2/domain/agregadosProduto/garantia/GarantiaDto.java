package com.techfix.beta2.domain.agregadosProduto.garantia;

public record GarantiaDto(
        Long id,
        Long fornecedor,
        String detalhesGarantia,
        Integer tempoGarantiaDias
) {
}
