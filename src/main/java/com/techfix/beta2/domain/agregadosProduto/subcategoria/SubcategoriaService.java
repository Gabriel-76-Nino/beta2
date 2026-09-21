package com.techfix.beta2.domain.agregadosProduto.subcategoria;

import com.techfix.beta2.domain.agregadosProduto.categoria.Categoria;
import com.techfix.beta2.domain.agregadosProduto.categoria.CategoriaRepository;
import com.techfix.beta2.domain.agregadosProduto.departamento.Departamento;
import com.techfix.beta2.domain.agregadosProduto.departamento.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoriaService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    public List<SubcategoriaDto> listarSubcategorias() {
        return subcategoriaRepository.findAll().stream()
                .map(s -> new SubcategoriaDto(s.getId(), s.getNomeSubcategoria(),
                        s.getDepartamento().getId(), s.getCategoria().getId())).toList();
    }

    public SubcategoriaDto cadastrarSubcategoria(SubcategoriaDto dto) {
        Departamento departamento = departamentoRepository.getReferenceById(dto.departamento());
        Categoria categoria = categoriaRepository.getReferenceById(dto.categoria());
        Subcategoria subcategoria = subcategoriaRepository.save(new Subcategoria(null, dto.nomeSubcategoria(), departamento, categoria));
        return new SubcategoriaDto(subcategoria.getId(), subcategoria.getNomeSubcategoria(), subcategoria.getDepartamento().getId(),
                subcategoria.getCategoria().getId());
    }
}
