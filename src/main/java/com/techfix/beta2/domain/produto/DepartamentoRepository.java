package com.techfix.beta2.domain.produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Departamento getReferenceByNomeDepartamento(String departamento);
}
