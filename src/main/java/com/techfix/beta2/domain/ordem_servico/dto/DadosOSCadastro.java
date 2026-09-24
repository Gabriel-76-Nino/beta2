package com.techfix.beta2.domain.ordem_servico.dto;

import com.techfix.beta2.domain.aparelho.TipoAparelho;
import com.techfix.beta2.domain.ordem_servico.StatusOS;

import java.util.Map;

public record DadosOSCadastro(
        Long id,
        Long pessoa,
        Long aparelhoCliente,
        String problemaRelatado,
        String acessoriosCliente,
        String atendenteRecebeu,
        StatusOS statusOS,
        Long idAparelhoCliente,
        Long produto,
        TipoAparelho tipoAparelho,
        Map<String, Object> especificacoes,
        String imei
){
}
