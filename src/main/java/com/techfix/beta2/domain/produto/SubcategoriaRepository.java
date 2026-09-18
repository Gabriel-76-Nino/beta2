package com.techfix.beta2.domain.produto;


import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {
    Subcategoria getReferenceByNomeSubcategoria(String subcategoria);
}
