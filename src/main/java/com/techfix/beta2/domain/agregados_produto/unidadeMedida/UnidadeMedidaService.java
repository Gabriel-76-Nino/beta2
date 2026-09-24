package com.techfix.beta2.domain.agregados_produto.unidadeMedida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeMedidaService {

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    public List<UnidadeMedidaDto> listarUnidadesMedidas() {
        return unidadeMedidaRepository.findAll().stream()
                .map(u -> new UnidadeMedidaDto(u.getId(),
                        u.getCodigo(), u.getDescricao(), u.getMultiplicador())).toList();
    }

    public UnidadeMedidaDto cadastrarUnidadeMedida(UnidadeMedidaDto dto) {
        UnidadeMedida unidadeMedida = unidadeMedidaRepository.save(
                new UnidadeMedida(null, dto.codigo(), dto.descricao(), dto.multiplicador()));
        return new UnidadeMedidaDto(unidadeMedida.getId(), unidadeMedida.getCodigo(),
                unidadeMedida.getDescricao(), unidadeMedida.getMultiplicador());
    }
}
