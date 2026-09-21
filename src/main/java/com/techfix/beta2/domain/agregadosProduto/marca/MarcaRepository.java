package com.techfix.beta2.domain.agregadosProduto.marca;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Marca getReferenceByNomeMarca(String marca);
}
