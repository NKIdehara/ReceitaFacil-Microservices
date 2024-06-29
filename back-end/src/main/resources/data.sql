INSERT INTO tbl_receitas (usuario, nome, preparo, figura, create_date) VALUES
   ('nOCEzyRcfXN3UqtSlfyrqzgsOKL2', 'Arroz', 'Lavar o arroz; colocar sal; cozinhar por 30 minutos.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_1.png?alt=media&token=30015b83-a563-4bc8-8e90-8f92e40f19c3', SELECT CURRENT_DATE()),
   ('nOCEzyRcfXN3UqtSlfyrqzgsOKL2', 'Carne', 'Temperar a carne; assar ate dourar.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_2.png?alt=media&token=6ace9d66-e77c-420c-a85f-1d6841acb6f0', SELECT CURRENT_DATE()),
   ('nOCEzyRcfXN3UqtSlfyrqzgsOKL2', 'Macarrão', 'Cozinhar o macarrão; escorrer e colocar o molho.', 'https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_3.png?alt=media&token=47038466-478b-423a-8e11-5c74f88f4d2a', SELECT CURRENT_DATE());

INSERT INTO tbl_ingredientes (item, quantidade, medida, id) VALUES 
   ('arroz', 1.0, 'xícara', 1),
   ('sal', 0.5, 'colher',   1);

INSERT INTO tbl_ingredientes (item, quantidade, medida, id) VALUES
   ('bife', 1.0, 'pedaço',   2),
   ('tempero', 2, 'pitadas', 2);

INSERT INTO tbl_ingredientes (item, quantidade, medida, id) VALUES
   ('macarrão', 500.0, 'g', 3),
   ('molho de tomate', 1.0, 'lata', 3);
