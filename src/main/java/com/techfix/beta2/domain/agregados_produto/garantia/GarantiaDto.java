package com.techfix.beta2.domain.agregados_produto.garantia;

public record GarantiaDto(
        Long id,
        Long fornecedor,
        String detalhesGarantia,
        Integer tempoGarantiaDias
) {
}
