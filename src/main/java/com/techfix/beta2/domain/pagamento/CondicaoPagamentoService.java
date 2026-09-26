package com.techfix.beta2.domain.pagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CondicaoPagamentoService {

    @Autowired
    private CondicaoPagamentoRepository condicaoPagamentoRepository;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private DiasCondicaoPagamentoRepository diasRepository;

    public List<CondicaoPagamentoDto> listarCondicoesPagamento() {
        return condicaoPagamentoRepository.findAll().stream().map(
                c -> new CondicaoPagamentoDto(c.getId(), c.getIdFpg().getIdFpg(), c.getCondicao(), c.getQuantidadeParcelas(),
                        c.getDiasCondicaoPagamentos().stream()
                                .map(d -> new DiasParaCondicaoPagamentoDto(d.getDias())).toList())).toList();
    }

    public CondicaoPagamentoDto cadastrarCondicaoPagamento(CondicaoPagamentoDto dto) {
        FormaPagamento idFpg = formaPagamentoRepository.getReferenceById(dto.idFpg());
        CondicaoPagamento condicao = new CondicaoPagamento(dto, idFpg);
        List<DiasCondicaoPagamento> dias = dto.dias().stream()
                .map(d -> new DiasCondicaoPagamento(null, condicao, d.dias())).toList();
        condicao.setDiasEmCondicao(dias);
        System.out.println(dias);
        System.out.println(condicao);
        condicaoPagamentoRepository.save(condicao);
        diasRepository.saveAll(dias);
        return new CondicaoPagamentoDto(condicao.getId(), condicao.getIdFpg().getIdFpg(), condicao.getCondicao(), condicao.getQuantidadeParcelas(),
                condicao.getDiasCondicaoPagamentos().stream().map(d -> new DiasParaCondicaoPagamentoDto(d.getDias())).toList());
    }
}
