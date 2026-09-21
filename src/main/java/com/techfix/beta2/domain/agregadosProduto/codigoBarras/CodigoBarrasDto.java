package com.techfix.beta2.domain.agregadosProduto.codigoBarras;

public record CodigoBarrasDto(
        Long id,
        Long produto,
        String gtin,
        String unidadeMedida
) {
}
