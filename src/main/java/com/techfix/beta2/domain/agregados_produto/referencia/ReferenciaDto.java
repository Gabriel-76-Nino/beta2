package com.techfix.beta2.domain.agregados_produto.referencia;

public record ReferenciaDto(
        Long id,
        Long fornecedor,
        Long produto,
        String referencia
) {
}
