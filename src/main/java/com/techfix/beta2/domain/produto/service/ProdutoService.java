package com.techfix.beta2.domain.produto.service;

import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.pessoa.PessoaRepository;
import com.techfix.beta2.domain.produto.*;
import com.techfix.beta2.domain.agregadosProduto.categoria.Categoria;
import com.techfix.beta2.domain.agregadosProduto.categoria.CategoriaRepository;
import com.techfix.beta2.domain.agregadosProduto.departamento.Departamento;
import com.techfix.beta2.domain.agregadosProduto.departamento.DepartamentoRepository;
import com.techfix.beta2.domain.produto.dto.CadastroProdutoDto;
import com.techfix.beta2.domain.produto.dto.ProdutoDto;
import com.techfix.beta2.domain.agregadosProduto.garantia.Garantia;
import com.techfix.beta2.domain.agregadosProduto.garantia.GarantiaRepository;
import com.techfix.beta2.domain.agregadosProduto.marca.Marca;
import com.techfix.beta2.domain.agregadosProduto.marca.MarcaRepository;
import com.techfix.beta2.domain.agregadosProduto.subcategoria.Subcategoria;
import com.techfix.beta2.domain.agregadosProduto.subcategoria.SubcategoriaRepository;
import com.techfix.beta2.domain.agregadosProduto.unidadeMedida.UnidadeMedida;
import com.techfix.beta2.domain.agregadosProduto.unidadeMedida.UnidadeMedidaRepository;
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
                p.getDescricao(), p.getDescricaoComercial(), p.getTipo(), p.getMarca().getId(),
                p.getDepartamento().getId(), p.getCategoria().getId(),
                p.getSubcategoria().getId(), p.getFornecedor().getId(),
                p.getMargem(), p.getCustoMedio(), p.getPrecoVenda(), p.getPrecoSugestao(),
                p.getMinimo(), p.getMaximo(), p.getEstoque().getEstoqueContabil(),
                p.getGarantia().getId(), p.getUnidadeMedida().getId(), p.getFoto(),
                p.getAtivo())).toList();
    }

    public ProdutoDto cadastrarProduto(CadastroProdutoDto dto) {

        Marca marca = marcaRepository.getReferenceByNomeMarca(dto.marca());
        Departamento departamento = departamentoRepository.getReferenceByNomeDepartamento(dto.departamento());
        Categoria categoria = categoriaRepository.getReferenceByNomeCategoria(dto.categoria());
        Subcategoria subcategoria = subcategoriaRepository.getReferenceByNomeSubcategoria(dto.subcategoria());
        Pessoa fornecedor = pessoaRepository.getReferenceByNome(dto.fornecedor());
        Garantia garantia = garantiaRepository.getReferenceById(dto.garantia());
        UnidadeMedida unidadeMedida = unidadeMedidaRepository.getReferenceByCodigo(dto.unidadeMedida());

        Produto produto = new Produto(dto, marca, departamento, categoria, subcategoria, fornecedor,
                garantia, unidadeMedida);
        produtoRepository.save(produto);

        return new ProdutoDto(produto.getId(), produto.getDescricao(), produto.getDescricaoComercial(),
                produto.getTipo(), produto.getMarca().getId(), produto.getDepartamento().getId(),
                produto.getCategoria().getId(), produto.getSubcategoria().getId(), produto.getFornecedor().getId(),
                produto.getMargem(), produto.getCustoMedio(), produto.getPrecoVenda(), produto.getPrecoSugestao(),
                produto.getMinimo(), produto.getMaximo(), produto.getEstoque().getEstoqueContabil(),
                produto.getGarantia().getId(), produto.getUnidadeMedida().getId(), produto.getFoto(),
                produto.getAtivo());
    }
}
