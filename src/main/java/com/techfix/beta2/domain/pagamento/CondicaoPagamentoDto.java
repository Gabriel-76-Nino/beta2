package com.techfix.beta2.domain.pagamento;

public record CondicaoPagamentoDto(
        Long id,
        Long idFpg,
        String condicao,
        Integer quatidadeParcelas
) {
}
