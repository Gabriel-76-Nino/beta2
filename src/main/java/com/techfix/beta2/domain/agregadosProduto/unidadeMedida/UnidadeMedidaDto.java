package com.techfix.beta2.domain.agregadosProduto.unidadeMedida;

public record UnidadeMedidaDto(
        Long id,
        String codigo,
        String descricao,
        Integer multiplicador
) {
}
