package com.techfix.beta2.domain.ordem_servico;

import com.techfix.beta2.domain.aparelho.TipoAparelho;
import com.techfix.beta2.domain.aparelho_cliente.*;
import com.techfix.beta2.domain.orcamento.OrcamentoRepository;
import com.techfix.beta2.domain.ordem_servico.dto.*;
import com.techfix.beta2.domain.ordem_servico.service.OrdemServicoService;
import com.techfix.beta2.domain.pessoa.*;
import com.techfix.beta2.domain.produto.*;
import com.techfix.beta2.domain.usuario.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdemServicoServiceTest {
    @Mock OrcamentoRepository orcamentoRepository;
    @Mock PessoaRepository pessoaRepository;
    @Mock OrdemServicoRepository ordemServicoRepository;
    @Mock UsuarioRepository usuarioRepository;
    @Mock ProdutoRepository produtoRepository;
    @Mock AparelhoClienteRepository aparelhoClienteRepository;
    @InjectMocks OrdemServicoService service;

    @Test
    void cadastraAparelhoNovoSemConsultarIdNulo() {
        Pessoa pessoa = mock(Pessoa.class);
        Usuario usuario = mock(Usuario.class);
        Produto produto = mock(Produto.class);
        when(pessoaRepository.getReferenceById(11L)).thenReturn(pessoa);
        when(usuarioRepository.getReferenceByNomeUsuario("atendente")).thenReturn(usuario);
        when(produtoRepository.getReferenceById(22L)).thenReturn(produto);
        when(pessoa.getId()).thenReturn(11L);
        when(produto.getId()).thenReturn(22L);
        when(usuario.getNomeUsuario()).thenReturn("atendente");
        var aparelho = new AparelhoClienteDto(null, 22L, TipoAparelho.CELULAR, Map.of(), "TESTE", 11L);
        var dto = new DadosOSCadastro(null, 11L, null, aparelho, "Tela", "Capa", "atendente", null);
        var result = service.cadastrarOS(dto);
        assertEquals(22L, result.aparelhoClienteDto().produto());
        verify(aparelhoClienteRepository, never()).findById(any());
        verify(aparelhoClienteRepository).save(any(AparelhoCliente.class));
        verify(ordemServicoRepository).save(any(OrdemServico.class));
    }

    @Test
    void alteraStatusDeOsSemTecnicoAtribuido() {
        OrdemServico os = mock(OrdemServico.class);
        Pessoa pessoa = mock(Pessoa.class);
        Usuario atendente = mock(Usuario.class);
        AparelhoCliente aparelho = mock(AparelhoCliente.class);
        Produto produto = mock(Produto.class);
        when(ordemServicoRepository.getReferenceById(1L)).thenReturn(os);
        when(os.getPessoa()).thenReturn(pessoa);
        when(os.getAparelhoCliente()).thenReturn(aparelho);
        when(aparelho.getProduto()).thenReturn(produto);
        when(aparelho.getPessoa()).thenReturn(pessoa);
        when(os.getAtendenteRecebeu()).thenReturn(atendente);
        when(os.getOrcamento()).thenReturn(List.of());
        var result = service.alterarStatusOS(new AlterarStatusOS(1L, StatusOS.REPROVADO));
        assertNull(result.tecnicoResponsavel());
        verify(os).setStatusOS(StatusOS.REPROVADO);
    }
}
