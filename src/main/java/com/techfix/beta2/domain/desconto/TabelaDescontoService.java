package com.techfix.beta2.domain.desconto;

import com.techfix.beta2.domain.agregados_produto.categoria.Categoria;
import com.techfix.beta2.domain.agregados_produto.categoria.CategoriaRepository;
import com.techfix.beta2.domain.agregados_produto.departamento.Departamento;
import com.techfix.beta2.domain.agregados_produto.departamento.DepartamentoRepository;
import com.techfix.beta2.domain.agregados_produto.subcategoria.Subcategoria;
import com.techfix.beta2.domain.agregados_produto.subcategoria.SubcategoriaRepository;
import com.techfix.beta2.domain.pagamento.FormaPagamento;
import com.techfix.beta2.domain.pagamento.FormaPagamentoRepository;
import com.techfix.beta2.domain.produto.Produto;
import com.techfix.beta2.domain.produto.ProdutoRepository;
import com.techfix.beta2.domain.usuario.Usuario;
import com.techfix.beta2.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabelaDescontoService {

    @Autowired
    private TabelaDescontoRepository tabelaDescontoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<TabelaDescontoDto> listarTabelasDesconto() {
        return tabelaDescontoRepository.findAll().stream()
                .map(t -> new TabelaDescontoDto(t.getId(), t.getUsuario().getNomeUsuario(),
                        t.getDepartamento().getId(), t.getCategoria().getId(), t.getSubcategoria().getId(),
                        t.getFormaPagamento().getIdFpg(), t.getProduto().getId(), t.getDesconto())).toList();
    }

    public TabelaDescontoDto cadastrarTabelaDesconto(TabelaDescontoDto dto) {
        Usuario usuario = usuarioRepository.getReferenceByNomeUsuario(dto.usuario());
        Departamento departamento = departamentoRepository.getReferenceById(dto.id());
        Categoria categoria = categoriaRepository.getReferenceById(dto.categoria());
        Subcategoria subcategoria = subcategoriaRepository.getReferenceById(dto.subcategoria());
        FormaPagamento formaPagamento = formaPagamentoRepository.getReferenceById(dto.formaPagamento());
        Produto produto = produtoRepository.getReferenceById(dto.produto());
        TabelaDesconto tabelaDesconto = new TabelaDesconto(null, usuario, departamento, categoria, subcategoria,
                formaPagamento, produto, dto.desconto());

        return new TabelaDescontoDto(tabelaDesconto.getId(), tabelaDesconto.getUsuario().getNomeUsuario(),
                tabelaDesconto.getDepartamento().getId(), tabelaDesconto.getCategoria().getId(),
                tabelaDesconto.getSubcategoria().getId(), tabelaDesconto.getFormaPagamento().getIdFpg(),
                tabelaDesconto.getProduto().getId(), tabelaDesconto.getDesconto());
    }
}
