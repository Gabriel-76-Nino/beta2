package com.techfix.beta2.domain.agregados_produto.garantia;

import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarantiaService {

    @Autowired
    private GarantiaRepository garantiaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<GarantiaDto> listarGarantias() {
        return garantiaRepository.findAll().stream()
                .map(g -> new GarantiaDto(g.getId(), g.getFornecedor().getId(),
                        g.getDetalhesGarantia(), g.getTempoGarantiaDias())).toList();
    }

    public GarantiaDto cadastrarGarantia(GarantiaDto dto) {
        Pessoa fornecedor = pessoaRepository.getReferenceById(dto.fornecedor());
        Garantia garantia = garantiaRepository.save(new Garantia(null, fornecedor, dto.detalhesGarantia(), dto.tempoGarantiaDias()));
        return new GarantiaDto(garantia.getId(), garantia.getFornecedor().getId(), garantia.getDetalhesGarantia(), garantia.getTempoGarantiaDias());
    }
}
