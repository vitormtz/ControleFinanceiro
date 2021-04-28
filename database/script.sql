CREATE TABLE tipo_despesa (id INT NOT NULL, descricao VARCHAR(25) NOT NULL,
						  CONSTRAINT pk_tipo_despesa PRIMARY KEY(id),
						  CONSTRAINT uq_tipo_despesa_descricao UNIQUE(descricao));

CREATE TABLE pessoa (id INT NOT NULL, nome VARCHAR(50) NOT NULL, email VARCHAR(75) NOT NULL, senha VARCHAR(100) NOT NULL,
					CONSTRAINT pk_pessoa PRIMARY KEY(id),
					CONSTRAINT uq_pessoa_email UNIQUE(email));

CREATE TABLE despesa (id INT NOT NULL, pessoa_id INT NOT NULL, tipo_despesa_id INT NOT NULL, valor DECIMAL(9,2) NOT NULL, descricao VARCHAR(50) NOT NULL,
					 CONSTRAINT pk_despesa PRIMARY KEY(id),
					 CONSTRAINT fk_pessoa_despesa FOREIGN KEY(pessoa_id) REFERENCES pessoa,
					 CONSTRAINT fk_tipo_despesa_despesa FOREIGN KEY(tipo_despesa_id) REFERENCES tipo_despesa);

CREATE TABLE receita (id INT NOT NULL, pessoa_id INT NOT NULL, valor DECIMAL(9,2) NOT NULL, descricao VARCHAR(50) NOT NULL,
					 CONSTRAINT pk_receita PRIMARY KEY(id),
					 CONSTRAINT fk_pessoa_receita FOREIGN KEY(id) REFERENCES pessoa);

CREATE TABLE integrante (id INT NOT NULL, pessoa_id INT NOT NULL, nome VARCHAR(50) NOT NULL,
						CONSTRAINT pk_integrante PRIMARY KEY(id),
						CONSTRAINT fk_pessoa_integrante FOREIGN KEY(pessoa_id) REFERENCES pessoa)