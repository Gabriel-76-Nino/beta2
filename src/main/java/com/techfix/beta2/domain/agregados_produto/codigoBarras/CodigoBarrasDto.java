package com.techfix.beta2.domain.agregados_produto.codigoBarras;

public record CodigoBarrasDto(
        Long id,
        Long produto,
        String gtin,
        String unidadeMedida
) {
}
