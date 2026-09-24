package com.techfix.beta2.domain.agregados_produto.unidadeMedida;

public record UnidadeMedidaDto(
        Long id,
        String codigo,
        String descricao,
        Integer multiplicador
) {
}
