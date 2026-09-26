package com.techfix.beta2.domain.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiasCondicaoPagamentoRepository extends JpaRepository<DiasCondicaoPagamento, Long> {
    @Query("select d from DiasCondicaoPagamento d where d.idCondicao in :condicaoPagamento")
    List<DiasCondicaoPagamento> listarTodosDiasPagamento(List<CondicaoPagamento> condicaoPagamento);
}
