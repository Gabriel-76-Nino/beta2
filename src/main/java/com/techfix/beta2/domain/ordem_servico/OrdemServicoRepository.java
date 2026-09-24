package com.techfix.beta2.domain.ordem_servico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdemServicoRepository extends JpaRepository <OrdemServico, Long> {

    @Query("SELECT o FROM OrdemServico o WHERE o.statusOS = AGUARDANDO_ATENDIMENTO OR o.statusOS = APROVADO_BALCAO")
    List<OrdemServico> buscarOSAbertas();
}
