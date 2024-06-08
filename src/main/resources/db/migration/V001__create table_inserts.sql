CREATE TABLE cliente (
  id bigint NOT NULL AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO `cliente` (`nome`) VALUES ('Zezinho 1');
INSERT INTO `cliente` (`nome`) VALUES ('Zezinho 2');
INSERT INTO `cliente` (`nome`) VALUES ('Zezinho 3');
INSERT INTO `cliente` (`nome`) VALUES ('Zezinho 4');
INSERT INTO `cliente` (`nome`) VALUES ('Zezinho 5');
INSERT INTO `cliente` (`nome`) VALUES ('Zezinho 6');
INSERT INTO `cliente` (`nome`) VALUES ('Zezinho 7');
INSERT INTO `cliente` (`nome`) VALUES ('Zezinho 8');
INSERT INTO `cliente` (`nome`) VALUES ('Zezinho 9');
INSERT INTO `cliente` (`nome`) VALUES ('Zezinho 10');

CREATE TABLE pedido (
  
  id bigint NOT NULL AUTO_INCREMENT,
  numero_controle bigint not null,
  data_cadastro date,
  nome_produto varchar(100) NOT NULL,
  valor decimal(12,2) NOT NULL,
  quantidade int not null,
  valor_total decimal(12,2) not null,
  cliente_id bigint NOT NULL,
  
  PRIMARY KEY (id),
  CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);




