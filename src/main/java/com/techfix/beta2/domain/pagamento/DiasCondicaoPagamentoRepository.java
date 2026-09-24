package com.techfix.beta2.domain.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiasCondicaoPagamentoRepository extends JpaRepository<DiasCondicaoPagamento, Long> {
    List<DiasCondicaoPagamento> findAllByIdCondicao(List<CondicaoPagamento> condicaoPagamento);
}
