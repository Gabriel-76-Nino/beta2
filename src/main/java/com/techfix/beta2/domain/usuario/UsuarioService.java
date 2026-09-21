package com.techfix.beta2.domain.usuario;

import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public UsuarioDto cadastrarUsuarios(UsuarioDto dto) {
        Pessoa pessoa = pessoaRepository.getReferenceById(dto.pessoa());
        Usuario usuario = usuarioRepository.save(new Usuario(null, dto.nomeUsuario(), pessoa, dto.funcao(), null, true));
        return new UsuarioDto(usuario.getId(), usuario.getNomeUsuario(), usuario.getPessoa().getId(), usuario.getFuncao(), usuario.getAtivo());
    }

    public List<UsuarioDto> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(u -> new UsuarioDto(u.getId(), u.getNomeUsuario(), u.getPessoa().getId(),
                        u.getFuncao(), u.getAtivo())).toList();
    }
}
