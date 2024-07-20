INSERT INTO tbl_medidas (nome_medida, tipo) VALUES
   ('g', 0),
   ('kg', 0),
   ('ml', 1),
   ('l', 1),
   ('xícara(s)', 1),
   ('colher(es) de sopa', 1),
   ('unidade(s)', 2);

INSERT INTO tbl_items (descricao, preco, medida_id) VALUES
   ('arroz', 0.0, 2),
   ('sal', 0.0, 1),
   ('bife', 0.0, 2),
   ('tempero', 0.0, 1),
   ('macarrão', 0.0, 1),
   ('molho de tomate', 0.0, 3),
   ('leite condensado', 0.0, 3),
   ('ovo', 0.0, 7),
   ('leite', 0.0, 3),
   ('açúcar', 0.0, 5),
   ('manteiga', 0.0, 6),
   ('farinha de trigo', 0.0, 1),
   ('fermento', 0.0, 6),
   ('chocolate picado', 0.0, 1),
   ('essência de baunilha', 0.0, 6);

INSERT INTO tbl_receitas (usuario, nome, preparo, figura, created_by, created_date) VALUES
   ('nOCEzyRcfXN3UqtSlfyrqzgsOKL2', 'Arroz', 'Lavar o arroz; colocar sal; cozinhar por 30 minutos.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_1.png?alt=media&token=30015b83-a563-4bc8-8e90-8f92e40f19c3', 'SYSTEM', SELECT CURRENT_DATE()),
   ('nOCEzyRcfXN3UqtSlfyrqzgsOKL2', 'Carne', 'Temperar a carne; assar ate dourar.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_2.png?alt=media&token=6ace9d66-e77c-420c-a85f-1d6841acb6f0', 'SYSTEM', SELECT CURRENT_DATE()),
   ('nOCEzyRcfXN3UqtSlfyrqzgsOKL2', 'Macarrão', 'Cozinhar o macarrão; escorrer e colocar o molho.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_3.png?alt=media&token=47038466-478b-423a-8e11-5c74f88f4d2a', 'SYSTEM', SELECT CURRENT_DATE());


INSERT INTO tbl_ingredientes (item_id, quantidade, medida_id, receita_id) VALUES
   (1, 500.0, 1, 1),
   (2, 0.5, 6, 1);

INSERT INTO tbl_ingredientes (item_id, quantidade, medida_id, receita_id) VALUES
   (3, 0.5, 2, 2),
   (4, 0.5, 6, 2);

INSERT INTO tbl_ingredientes (item_id, quantidade, medida_id, receita_id) VALUES
   (5, 500.0, 1, 3),
   (6, 350.0, 3, 3);

INSERT INTO tbl_receitas (usuario, nome, preparo, figura, created_by, created_date) VALUES
   ('gNr4VRTVUHXBCvyaLm6ZYaxup1s2', 'Pudim', 'Bater os ovos e acrescentar o leite e leite condensado; assar em banho maria por 45 min.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_4.png?alt=media&token=c4c04998-d7f6-4670-82ef-b1bfc745a6a9', 'SYSTEM', SELECT CURRENT_DATE()),
   ('gNr4VRTVUHXBCvyaLm6ZYaxup1s2', 'Bolo', 'Misturar os ingredientes até ficar uniforme; assar em forno médio por 40 min.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_5.png?alt=media&token=6cd2ffce-bcbd-44cc-b995-70e0d07ab7be', 'SYSTEM', SELECT CURRENT_DATE()),
   ('gNr4VRTVUHXBCvyaLm6ZYaxup1s2', 'Cookie', 'Misturar o açúcar, manteiga e baunilha; acrescentar os ovos e a farinha aos poucos enquanto mistura; juntar o fermento à massa; no final, misturar o chocolate ficado; forme bolinhas pequenas e assar em forno médio por 20 min.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_6.png?alt=media&token=a2cf6f88-fdc8-4dc0-8ddd-f96cce2a70bb', 'SYSTEM', SELECT CURRENT_DATE());

INSERT INTO tbl_ingredientes (item_id, quantidade, medida_id, receita_id) VALUES
   (7, 350.0, 3, 4),
   (8, 3.0, 7, 4),
   (9, 300.0, 3, 4);

INSERT INTO tbl_ingredientes (item_id, quantidade, medida_id, receita_id) VALUES
   (10, 2.0, 5, 5),
   (11, 4.0, 6, 5),
   (9, 150.0, 3, 5),
   (12, 300.0, 1, 5),
   (8, 3.0, 7, 5),
   (13, 1.0, 6, 5);

INSERT INTO tbl_ingredientes (item_id, quantidade, medida_id, receita_id) VALUES
   (11, 125.0, 1, 6),
   (10, 1.0, 5, 6),
   (12, 300.0, 1, 6),
   (14, 300.0, 1, 6),
   (8, 2.0, 7, 6),
   (13, 1.0, 6, 6),
   (15, 0.5, 6, 6);