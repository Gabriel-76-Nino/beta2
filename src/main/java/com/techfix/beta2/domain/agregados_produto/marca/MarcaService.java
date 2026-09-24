package com.techfix.beta2.domain.agregados_produto.marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<MarcaDto> listarMarcas() {
        return marcaRepository.findAll().stream()
                .map(m -> new MarcaDto(m.getId(), m.getNomeMarca())).toList();
    }

    public MarcaDto cadastrarMarca(MarcaDto dto) {
        Marca marca = marcaRepository.save(new Marca(null, dto.nomeMarca()));
        return new MarcaDto(marca.getId(), marca.getNomeMarca());
    }
}
