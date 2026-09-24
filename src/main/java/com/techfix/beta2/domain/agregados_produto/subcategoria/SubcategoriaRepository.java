package com.techfix.beta2.domain.agregados_produto.subcategoria;


import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {
    Subcategoria getReferenceByNomeSubcategoria(String subcategoria);
}
