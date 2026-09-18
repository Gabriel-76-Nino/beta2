package com.techfix.beta2.domain.produto;

public record UnidadeMedidaDto(
        Long id,
        String codigo,
        String descricao,
        Integer multiplicador
) {
}
