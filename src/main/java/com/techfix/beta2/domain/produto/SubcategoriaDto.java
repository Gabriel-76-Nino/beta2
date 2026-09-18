package com.techfix.beta2.domain.produto;

public record SubcategoriaDto(
        Long id,
        String nomeSubcategoria,
        String departamento,
        String categoria
) {
}
