alter table ordens_servicos
    add constraint fk_ordens_servicos_usuario_atendeu
        foreign key (atendente_recebeu)
            references usuarios(nome_usuario)