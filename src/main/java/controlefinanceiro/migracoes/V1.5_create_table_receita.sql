--liquibase formatted sql

--changeset Vitor:5
CREATE TABLE receita (
	id INT NOT NULL, 
	pessoa_id INT, 
	integrante_id INT, 
	valor DECIMAL(9,2) NOT NULL, 
	descricao VARCHAR(50) NOT NULL, 
	data_acao TIMESTAMP NOT NULL, 
	CONSTRAINT pk_receita PRIMARY KEY(id), 
	CONSTRAINT fk_pessoa_receita FOREIGN KEY(pessoa_id) REFERENCES pessoa, 
	CONSTRAINT fk_integrante_receita FOREIGN KEY(integrante_id) REFERENCES integrante
);