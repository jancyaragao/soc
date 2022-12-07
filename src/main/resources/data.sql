INSERT INTO tb_exame (nome, descricao) VALUES ('Sangue', 'Exame de sangue');
INSERT INTO tb_exame (nome, descricao) VALUES ('Fezes', 'Exame de fezes');
INSERT INTO tb_exame (nome, descricao) VALUES ('Urina', 'Exame de urina');
INSERT INTO tb_exame (nome, descricao) VALUES ('Ultrassonografia', 'Exame de ultrassonografia');
INSERT INTO tb_exame (nome, descricao) VALUES ('Endoscopia', 'Exame de endoscopia');

INSERT INTO tb_funcionario (nome, idade) VALUES ('Jâncy Aragão', 23);
INSERT INTO tb_funcionario (nome, idade) VALUES ('Thais Moura', 25);
INSERT INTO tb_funcionario (nome, idade) VALUES ('Silvana Cariolano', 40);
INSERT INTO tb_funcionario (nome, idade) VALUES ('Jâncy Roni', 45);
INSERT INTO tb_funcionario (nome, idade) VALUES ('Jâncy Rafael', 9);

INSERT INTO tb_marcacao (funcionario_codigo, exame_codigo, data_exame, status_exame) VALUES (1, 5, '2022-12-12', 'PENDENTE');
INSERT INTO tb_marcacao (funcionario_codigo, exame_codigo, data_exame, status_exame) VALUES (2, 4, '2022-12-31', 'PENDENTE');
INSERT INTO tb_marcacao (funcionario_codigo, exame_codigo, data_exame, status_exame) VALUES (3, 3, '2022-12-10', 'PENDENTE');
INSERT INTO tb_marcacao (funcionario_codigo, exame_codigo, data_exame, status_exame) VALUES (4, 2, '2022-12-10', 'PENDENTE');
INSERT INTO tb_marcacao (funcionario_codigo, exame_codigo, data_exame, status_exame) VALUES (5, 1, '2023-12-10', 'PENDENTE');