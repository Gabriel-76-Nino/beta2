package com.techfix.beta2.domain.agregadosProduto.referencia;

public record ReferenciaDto(
        Long id,
        Long fornecedor,
        Long produto,
        String referencia
) {
}
