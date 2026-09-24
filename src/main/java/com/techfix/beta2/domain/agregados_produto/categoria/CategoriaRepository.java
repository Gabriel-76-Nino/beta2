package com.techfix.beta2.domain.agregados_produto.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria getReferenceByNomeCategoria(String categoria);
}
