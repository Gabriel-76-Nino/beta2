package com.techfix.beta2.domain.movimentacao_mercadoria;

public record TipoMovimentacaoDto(
        Long codigoMovimentacao,
        String descricaoMovimentacao,
        Operacao operacao
) {
}
