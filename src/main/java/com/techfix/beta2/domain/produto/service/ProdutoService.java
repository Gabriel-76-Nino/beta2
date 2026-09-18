package com.techfix.beta2.domain.produto.service;

import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.pessoa.PessoaRepository;
import com.techfix.beta2.domain.produto.*;
import com.techfix.beta2.domain.produto.dto.CadastroProdutoDto;
import com.techfix.beta2.domain.produto.dto.ProdutoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private GarantiaRepository garantiaRepository;

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    public List<ProdutoDto> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(p -> new ProdutoDto(p.getId(),
                p.getDescricao(), p.getDescricaoComercial(), p.getTipo(), p.getMarca().getNomeMarca(),
                p.getDepartamento().getNomeDepartamento(), p.getCategoria().getNomeCategoria(),
                p.getSubcategoria().getNomeSubcategoria(), p.getFornecedor().getNome(),
                p.getMargem(), p.getCustoMedio(), p.getPrecoVenda(), p.getPrecoSugestao(),
                p.getMinimo(), p.getMaximo(), p.getEstoque().getEstoqueContabil(),
                p.getGaratia().getId(), p.getUnidadeMedida().getCodigo(), p.getFoto(),
                p.getAtivo())).toList();
    }

    public void cadastrarProduto(CadastroProdutoDto dto) {

        Marca marca = marcaRepository.getReferenceByNomeMarca(dto.marca());
        Departamento departamento = departamentoRepository.getReferenceByNomeDepartamento(dto.departamento());
        Categoria categoria = categoriaRepository.getReferenceByNomeCategoria(dto.categoria());
        Subcategoria subcategoria = subcategoriaRepository.getReferenceByNomeSubcategoria(dto.subcategoria());
        Pessoa fornecedor = pessoaRepository.getReferenceByNome(dto.fornecedor());
        Garatia garantia = garantiaRepository.getReferenceById(dto.garantia());
        UnidadeMedida unidadeMedida = unidadeMedidaRepository.getReferenceByCodigo(dto.unidadeMedida());

        Produto produto = new Produto(dto, marca, departamento, categoria, subcategoria, fornecedor,
                garantia, unidadeMedida);
        produtoRepository.save(produto);
    }
}
