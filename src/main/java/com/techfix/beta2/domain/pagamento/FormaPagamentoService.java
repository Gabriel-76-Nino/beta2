package com.techfix.beta2.domain.pagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamentoDto> listarFormasPagamento() {
        return formaPagamentoRepository.findAll().stream()
                .map(f -> new FormaPagamentoDto(f.getIdFpg(), f.getDescricaoFpg())).toList();
    }

    public FormaPagamentoDto cadastrarFormaPagamento(FormaPagamentoDto dto) {
        FormaPagamento formaPagamento = formaPagamentoRepository.getReferenceById(dto.idFpg());
        return new FormaPagamentoDto(formaPagamento.getIdFpg(), formaPagamento.getDescricaoFpg());
    }
}
