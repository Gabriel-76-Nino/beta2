package com.techfix.beta2.domain.agregadosProduto.subcategoria;

public record SubcategoriaDto(
        Long id,
        String nomeSubcategoria,
        Long departamento,
        Long categoria
) {
}
