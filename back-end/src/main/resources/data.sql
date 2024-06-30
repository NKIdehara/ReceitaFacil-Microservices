INSERT INTO tbl_medidas (nome_medida, tipo) VALUES
   ('g', 0),
   ('kg', 0),
   ('ml', 1),
   ('l', 1),
   ('xícara(s)', 1),
   ('colher(es) de sopa', 1),
   ('unidade(s)', 2);


INSERT INTO tbl_receitas (usuario, nome, preparo, figura, custo, created_by, created_date) VALUES
   ('nOCEzyRcfXN3UqtSlfyrqzgsOKL2', 'Arroz', 'Lavar o arroz; colocar sal; cozinhar por 30 minutos.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_1.png?alt=media&token=30015b83-a563-4bc8-8e90-8f92e40f19c3', 0.0, 'SYSTEM', SELECT CURRENT_DATE()),
   ('nOCEzyRcfXN3UqtSlfyrqzgsOKL2', 'Carne', 'Temperar a carne; assar ate dourar.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_2.png?alt=media&token=6ace9d66-e77c-420c-a85f-1d6841acb6f0', 0.0, 'SYSTEM', SELECT CURRENT_DATE()),
   ('nOCEzyRcfXN3UqtSlfyrqzgsOKL2', 'Macarrão', 'Cozinhar o macarrão; escorrer e colocar o molho.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_3.png?alt=media&token=47038466-478b-423a-8e11-5c74f88f4d2a', 0.0, 'SYSTEM', SELECT CURRENT_DATE());

INSERT INTO tbl_ingredientes (item, quantidade, medida, id) VALUES 
   ('arroz', 1.0, 'xícara', 1),
   ('sal', 0.5, 'colher',   1);

INSERT INTO tbl_ingredientes (item, quantidade, medida, id) VALUES
   ('bife', 1.0, 'pedaço',   2),
   ('tempero', 2.0, 'pitadas', 2);

INSERT INTO tbl_ingredientes (item, quantidade, medida, id) VALUES
   ('macarrão', 500.0, 'g', 3),
   ('molho de tomate', 1.0, 'lata', 3);

INSERT INTO tbl_receitas (usuario, nome, preparo, figura, custo, created_by, created_date) VALUES
   ('gNr4VRTVUHXBCvyaLm6ZYaxup1s2', 'Pudim', 'Bater os ovos e acrescentar o leite e leite condensado; assar em banho maria por 45 min.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_4.png?alt=media&token=c4c04998-d7f6-4670-82ef-b1bfc745a6a9', 0.0, 'SYSTEM', SELECT CURRENT_DATE()),
   ('gNr4VRTVUHXBCvyaLm6ZYaxup1s2', 'Bolo', 'Misturar os ingredientes até ficar uniforme; assar em forno médio por 40 min.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_5.png?alt=media&token=6cd2ffce-bcbd-44cc-b995-70e0d07ab7be', 0.0, 'SYSTEM', SELECT CURRENT_DATE()),
   ('gNr4VRTVUHXBCvyaLm6ZYaxup1s2', 'Cookie', 'Misturar o açúcar, manteiga e baunilha; acrescentar os ovos e a farinha aos poucos enquanto mistura; juntar o fermento à massa; no final, misturar o chocolate ficado; forme bolinhas pequenas e assar em forno médio por 20 min.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_6.png?alt=media&token=a2cf6f88-fdc8-4dc0-8ddd-f96cce2a70bb', 0.0, 'SYSTEM', SELECT CURRENT_DATE());

INSERT INTO tbl_ingredientes (item, quantidade, medida, id) VALUES 
   ('leite condensado', 1.0, 'lata', 4),
   ('ovos', 3.0, 'unidades', 4),
   ('leite', 300.0, 'ml',   4);

INSERT INTO tbl_ingredientes (item, quantidade, medida, id) VALUES
   ('açúcar', 2.0, 'xícaras',   5),
   ('manteiga', 4.0, 'colheres de sopa',   5),
   ('leite', 150.0, 'ml',   5),
   ('farinha de trigo', 300.0, 'g',   5),
   ('ovos', 3.0, 'unidades',   5),
   ('fermento', 1.0, 'colher de sopa', 5);

INSERT INTO tbl_ingredientes (item, quantidade, medida, id) VALUES
   ('manteiga', 125.0, 'g', 6),
   ('açúcar', 1.0, 'xícara', 6),
   ('farinha de trigo', 300.0, 'g', 6),
   ('chocolate picado', 300.0, 'g', 6),
   ('ovos', 2.0, 'unidades', 6),
   ('fermento', 1.0, 'colher de sopa', 6),
   ('essência de baunilha', 0.5, 'colher de sopa', 6);