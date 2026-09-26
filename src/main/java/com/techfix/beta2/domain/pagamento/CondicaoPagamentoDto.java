package com.techfix.beta2.domain.pagamento;

import java.util.List;

public record CondicaoPagamentoDto(
        Long id,
        Long idFpg,
        String condicao,
        Integer quatidadeParcelas,
        List<DiasParaCondicaoPagamentoDto> dias
) {
}
