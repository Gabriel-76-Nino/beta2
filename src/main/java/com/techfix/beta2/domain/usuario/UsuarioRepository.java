package com.techfix.beta2.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario getReferenceByNomeUsuario(String usuario);
}
