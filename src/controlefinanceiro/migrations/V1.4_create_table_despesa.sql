--liquibase formatted sql

--changeset Vitor:4
CREATE TABLE despesa (
	id INT NOT NULL, 
	pessoa_id INT, 
	integrante_id INT, 
	tipo_despesa_id INT NOT NULL, 
	valor DECIMAL(9,2) NOT NULL, 
	descricao VARCHAR(50) NOT NULL, 
	data_acao TIMESTAMP NOT NULL, 
	CONSTRAINT pk_despesa PRIMARY KEY(id), 
	CONSTRAINT fk_pessoa_despesa FOREIGN KEY(pessoa_id) REFERENCES pessoa, 
	CONSTRAINT fk_integrante_despesa FOREIGN KEY(integrante_id) REFERENCES integrante, 
	CONSTRAINT fk_tipo_despesa_despesa FOREIGN KEY(tipo_despesa_id) REFERENCES tipo_despesa
);