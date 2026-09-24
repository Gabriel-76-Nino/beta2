package com.techfix.beta2.domain.agregados_produto.categoria;

import com.techfix.beta2.domain.agregados_produto.departamento.Departamento;
import com.techfix.beta2.domain.agregados_produto.departamento.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDto> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(c -> new CategoriaDto(c.getId(), c.getNomeCategoria(),
                        c.getDepartamento().getId())).toList();
    }

    public CategoriaDto cadastrarCategoria(CategoriaDto dto) {
        Departamento departamento = departamentoRepository.getReferenceById(dto.departamento());
        Categoria categoria = categoriaRepository.save(new Categoria(null, dto.nomeCategoria(), departamento, null));
        return new CategoriaDto(categoria.getId(), categoria.getNomeCategoria(), categoria.getDepartamento().getId());
    }
}
