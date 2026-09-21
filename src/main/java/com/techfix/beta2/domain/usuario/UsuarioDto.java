package com.techfix.beta2.domain.usuario;

public record UsuarioDto(
        Long id,
        String nomeUsuario,
        Long pessoa,
        Funcao funcao,
        Boolean ativo
) {
}
