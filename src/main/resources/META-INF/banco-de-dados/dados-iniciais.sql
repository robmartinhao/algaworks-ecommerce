insert into algaworks_ecommerce.produto (id, nome, data_criacao, preco, descricao) values (1, 'Kindle', date_sub(sysdate(), interval 1 day), 499.0, 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into algaworks_ecommerce.produto (id, nome, data_criacao, preco, descricao) values (3, 'Câmera GoPro Hero 7', date_sub(sysdate(), interval 1 day), 1400.0, 'Desempenho 2x melhor.');

insert into algaworks_ecommerce.cliente(id, nome) values (1, "Osvaldo");
insert into algaworks_ecommerce.cliente(id, nome) values (2, "Roberto");

insert into algaworks_ecommerce.pedido (id, cliente_id, data_criacao, total, status) values (1, 1, sysdate(), 100.0, 'AGUARDANDO');

insert into algaworks_ecommerce.item_pedido (id, pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 1, 5.0, 2);

insert into algaworks_ecommerce.categoria(id, nome) values (1, "Eletrônicos");