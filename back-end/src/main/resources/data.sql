INSERT INTO tbl_receita (usuario, nome, preparo, data_receita, figura)
VALUES ('nOCEzyRcfXN3UqtSlfyrqzgsOKL2', 'Arroz', 'Lavar o arroz; colocar sal; cozinhar por 30 minutos.', '2024-01-01', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_1.png?alt=media&token=30015b83-a563-4bc8-8e90-8f92e40f19c3');

INSERT INTO tbl_receita (usuario, nome, preparo, data_receita, figura)
VALUES ('nOCEzyRcfXN3UqtSlfyrqzgsOKL2', 'Carne', 'Temperar a carne; assar ate dourar.', '2024-01-01', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_2.png?alt=media&token=6ace9d66-e77c-420c-a85f-1d6841acb6f0');

INSERT INTO tbl_receita (usuario, nome, preparo, data_receita, figura)
VALUES ('nOCEzyRcfXN3UqtSlfyrqzgsOKL2', 'Macarrao', 'Cozinhar o macarrao; escorrer e colocar o molho.', '2024-01-01', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_3.png?alt=media&token=47038466-478b-423a-8e11-5c74f88f4d2a');

INSERT INTO tbl_ingrediente (item, quantidade, medida, id_receita) VALUES ('arroz', 1.0, 'xicara', (SELECT id AS id_receita FROM tbl_receita WHERE nome = 'Arroz' LIMIT 1));
INSERT INTO tbl_ingrediente (item, quantidade, medida, id_receita) VALUES ('sal', 0.5, 'colher', (SELECT id AS id_receita FROM tbl_receita WHERE nome = 'Arroz' LIMIT 1));

INSERT INTO tbl_ingrediente (item, quantidade, medida, id_receita) VALUES ('bife', 1.0, 'pedaco', (SELECT id AS id_receita FROM tbl_receita WHERE nome = 'Carne' LIMIT 1));
INSERT INTO tbl_ingrediente (item, quantidade, medida, id_receita) VALUES ('tempero', 2, 'pitadas', (SELECT id AS id_receita FROM tbl_receita WHERE nome = 'Carne' LIMIT 1));

INSERT INTO tbl_ingrediente (item, quantidade, medida, id_receita) VALUES ('macarrao', 500.0, 'g', (SELECT id AS id_receita FROM tbl_receita WHERE nome = 'Macarrao' LIMIT 1));
INSERT INTO tbl_ingrediente (item, quantidade, medida, id_receita) VALUES ('molho de tomate', 1.0, 'lata', (SELECT id AS id_receita FROM tbl_receita WHERE nome = 'Macarrao' LIMIT 1));
