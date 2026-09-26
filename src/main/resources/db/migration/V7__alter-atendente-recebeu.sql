alter table ordens_servicos
drop constraint fk_ordens_servicos_usuario_atendeu;

alter table ordens_servicos
alter column atendente_recebeu type varchar(255)
using atendente_recebeu::varchar(255);

alter table ordens_servicos
    add constraint fk_ordens_servicos_usuario_atendeu
        foreign key (atendente_recebeu)
            references usuarios(nome_usuario)