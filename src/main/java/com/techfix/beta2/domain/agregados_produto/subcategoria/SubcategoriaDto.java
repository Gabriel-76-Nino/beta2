package com.techfix.beta2.domain.agregados_produto.subcategoria;

public record SubcategoriaDto(
        Long id,
        String nomeSubcategoria,
        Long departamento,
        Long categoria
) {
}
