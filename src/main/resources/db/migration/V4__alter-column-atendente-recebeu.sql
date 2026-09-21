alter table ordens_servicos
alter column atendente_recebeu type bigint
using atendente_recebeu::bigint

alter table ordens_servicos
add constraint fk_ordens_servicos_usuario_atendeu
    foreign key (atendente_recebeu)
    references usuarios(nome_usuario)