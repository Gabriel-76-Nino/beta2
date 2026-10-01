package com.techfix.beta2.domain.movimentacao_mercadoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoMovimentacaoService {

    @Autowired
    private TipoMovimentacaoRepository tipoMovimentacaoRepository;

    public List<TipoMovimentacaoDto> listarTiposMovimentacao() {
        return tipoMovimentacaoRepository.findAll().stream()
                .map(t -> new TipoMovimentacaoDto(
                        t.getCodigoMovimentacao(), t.getDescricaoMovimentacao(),
                        t.getOperacao()
                )).toList();
    }

    public TipoMovimentacaoDto cadastrarTiposMovimentacao(TipoMovimentacaoDto dto){
        TipoMovimentacao tipoMovimentacao = tipoMovimentacaoRepository.save(new TipoMovimentacao(dto));
        return new TipoMovimentacaoDto(tipoMovimentacao.getCodigoMovimentacao(),
                tipoMovimentacao.getDescricaoMovimentacao(),
                tipoMovimentacao.getOperacao());
    }
}
