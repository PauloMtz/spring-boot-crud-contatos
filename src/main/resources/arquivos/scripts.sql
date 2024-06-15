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

insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (1, '88702-135', 'Rua Amadio Vettoretti, 524', null, 'Oficinas', 'Tubarão', 'SC');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (2, '89031-075', 'Rua Presidente Ferreira de Brito, 649', 'Ap. 101, bl. 01', 'Do Salto', 'Blumenau', 'SC');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado) 
    values (3, '41270-015', 'Travessa Bom Jesus dos Milagres, 274', null, 'Campinas de Pirajá','Salvador','BA');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (4, '61944-200', 'Rua Raimundo Maria Sales, 597', null, 'Novo Maranguape II','Maranguape','','CE');

insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (1, 'Elza Luciana Giovanna Assunção', '28517020928', 'elza_assuncao@gruppoitalia.com.br', '(48) 3741-3185', 1);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (2, 'Geraldo Manuel Farias', '00856016918', 'geraldo_manuel_farias@mcexecutiva.com.br', '(47) 2736-9551', 2);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (3, 'Yuri Julio Julio Araújo', '06661492082','yuri_araujo@email.com','(71) 2747-5960', 3);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (4, 'Camila Aline Lara Teixeira', '66255859630','camila.aline.teixeira@pronta.com.br','(85) 3922-3437', 4);
