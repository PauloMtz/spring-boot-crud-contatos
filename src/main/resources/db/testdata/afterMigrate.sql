set foreign_key_checks = 0;

delete from enderecos;
delete from contatos;

set foreign_key_checks = 1;

alter table enderecos auto_increment = 1;
alter table contatos auto_increment = 1;

insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (1, '88702-135', 'Rua Amadio Vettoretti, 524', null, 'Oficinas', 'Tubarão', 'SC');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (2, '89031-075', 'Rua Presidente Ferreira de Brito, 649', 'Ap. 101, bl. 01', 'Do Salto', 'Blumenau', 'SC');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado) 
    values (3, '41270-015', 'Travessa Bom Jesus dos Milagres, 274', null, 'Campinas de Pirajá','Salvador','BA');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (4, '61944-200', 'Rua Raimundo Maria Sales, 597', null, 'Novo Maranguape II','Maranguape','CE');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (5, '31550-080', 'Rua Elias Moysés, 580', null, 'Copacabana', 'Belo Horizonte', 'MG');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (6, '31150-230', 'Rua Jaborandi, 629', 'Ap. 505', 'Cachoeirinha', 'Belo Horizonte', 'MG');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (7, '30521-030', 'Rua Silvio de Oliveira Pacheco, 736', null, 'Virgínia', 'Belo Horizonte', 'MG');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (8, '30670-220', 'Rua M, 525', null, 'Vila Pinho Vale do Jatobá', 'Belo Horizonte', 'MG');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (9, '08370-194', 'Viela Mariana, 193', null, 'Jardim São Gonçalo', 'São Paulo', 'SP');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (10, '05379-175', 'Travessa Benjamim Bellemo, 649A', 'Ap. 109', 'Rio Pequeno', 'São Paulo', 'SP');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (11, '03471-110', 'Rua Júlio César Porto, 348', null, 'Jardim Vila Formosa', 'São Paulo', 'SP');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (12, '05030-150', 'Praça Doutor Vicente Tramonte Garcia, 261', null, 'Jardim Vera Cruz', 'São Paulo', 'SP');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (13,'21012-110', 'Rua Marcelino de Brito, 960', 'Casa A', 'Cordovil', 'Rio de Janeiro', 'RJ');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (14, '24411-420', 'Rua Herval de Braga Muniz, 724', null, 'Zumbi', 'São Gonçalo', 'RJ');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (15, '26293-426', 'Rua Floranea, 572', 'Ap 101', 'Ipiranga', 'Nova Iguaçu', 'RJ');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (16, '21012-210', 'Rua Sá Freire, 949', null, 'Cordovil', 'Rio de Janeiro', 'RJ');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (17, '29070-600', 'Rua Professor Nilo Martins da Cunha, 886', null, 'Maria Ortiz', 'Vitória', 'ES');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (18, '29075-630', 'Avenida Fernando Ferrari, 622', 'Ap. 202', 'Aeroporto', 'Vitória', 'ES');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (19, '29027-467', 'Rua João Pereira da Costa, 967', 'Casa B', 'Bela Vista', 'Vitória', 'ES');
insert into enderecos (id, cep, endereco, complemento, bairro, cidade, estado)
    values (20, '29025-661', 'Escadaria Maximiniano Pasini, 922', null, 'Caratoíra', 'Vitória', 'ES');

insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (1, 'Elza Luciana Giovanna Assunção', '28517020928', 'elza_assuncao@gruppoitalia.com.br', '(48) 3741-3185', 1);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (2, 'Geraldo Manuel Farias', '00856016918', 'geraldo_manuel_farias@mcexecutiva.com.br', '(47) 2736-9551', 2);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (3, 'Yuri Julio Julio Araújo', '06661492082','yuri_araujo@email.com','(71) 2747-5960', 3);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (4, 'Camila Aline Lara Teixeira', '66255859630','camila.aline.teixeira@pronta.com.br','(85) 3922-3437', 4);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (5, 'Julia Rita Almada', '51880878631', 'julia.rita.almada@otlokk.com', '(31) 2982-5248', 5);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (6, 'Mariah Amanda Emilly Lima', '25559941654', 'mariah.amanda.lima@edepbr.com.br', '(31) 2554-5899', 6);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (7, 'Thiago Anderson Sebastião Ferreira', '92685212612', 'thiago_anderson_ferreira@mourafiorito.com', '(31) 3746-3729', 7);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (8, 'Luís Severino Drumond', '93140421621', 'luis-drumond70@fortlar.com.br', '(31) 2673-5087', 8);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (9, 'Helena Eloá Baptista', '33189351821', 'helena-baptista78@realweb.com.br', '(11) 2601-1278', 9);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (10, 'Benício Cauê Kauê Pires', '90634109804', 'benicio_pires@gmindustria.com.br', '(11) 3728-5691', 10);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (11, 'Alexandre Bruno Porto', '63182909827', 'alexandre_bruno_porto@patrilarm.com.br', '(11) 2730-0754', 11);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (12, 'Joaquim Enzo Antonio Rodrigues', '28803260870', 'joaquim_enzo_rodrigues@franciscofilho.adv.br', '(11) 2760-7508',  12);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (13, 'Kauê César Cauã Brito', '54974660799', 'kaue-brito91@jcoronel.com.br', '(21) 2586-9152', 13);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (14, 'Anthony Cauê Barros', '58640201765', 'anthonycauebarros@amure.com.br', '(21) 3617-1728', 14);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (15, 'Ana Lorena Pires', '39202381763', 'ana-pires74@sent.com', '(21) 2885-5285', 15);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (16, 'Marlene Andrea Porto', '12382055774', 'marlene.andrea.porto@plani.com.br', '(21) 3821-1203', 16);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (17, 'Vinicius Matheus Benjamin Cavalcanti', '97782805767', 'vinicius-cavalcanti96@contabilidadevictoria.com.br', '(27) 2836-9658', 17);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (18, 'Carla Daiane da Costa', '91079766740', 'carla_daiane_dacosta@predilectasorocaba.com.br', '(27) 3943-6635', 18);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (19, 'Anderson Eduardo Alves', '72409491766', 'anderson.eduardo.alves@andressamelo.com.br', '(27) 2898-8190', 19);
insert into contatos (id, nome, cpf, email, telefone, endereco_id)
    values (20, 'Roberto Manuel Diego da Cruz', '69892938704', 'roberto.manuel.dacruz@mega.com.br', '(27) 3894-2735', 20);
