package com.techfix.beta2.domain.agregadosProduto.unidadeMedida;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long> {
    UnidadeMedida getReferenceByCodigo(String codigo);
}
