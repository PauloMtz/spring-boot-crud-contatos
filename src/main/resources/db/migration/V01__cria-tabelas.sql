create table enderecos (
    id bigint not null auto_increment,
    bairro varchar(60),
    cep varchar(9),
    cidade varchar(60),
    complemento varchar(60),
    endereco varchar(60),
    estado varchar(2),
    primary key (id)
) engine=InnoDB default charset=utf8;

create table contatos (
    id bigint not null auto_increment,
    cpf varchar(11) not null,
    email varchar(60) not null,
    nome varchar(60),
    telefone varchar(14),
    endereco_id bigint,
    primary key (id)
) engine=InnoDB default charset=utf8;