package com.techfix.beta2.domain.agregadosProduto.departamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;


    public DepartamentoDto cadatrarDepartamento(DepartamentoDto dto) {
        Departamento departamento = departamentoRepository.save(new Departamento(null, dto.nomeDepartamento(), null, null));
        return new DepartamentoDto(departamento.getId(), departamento.getNomeDepartamento());
    }

    public List<DepartamentoDto> listarDepartamentos() {
        return departamentoRepository.findAll().stream()
                .map(d -> new DepartamentoDto(d.getId(), d.getNomeDepartamento())).toList();
    }
}
