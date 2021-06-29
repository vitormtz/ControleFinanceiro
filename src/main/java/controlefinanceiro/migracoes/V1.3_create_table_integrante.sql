--liquibase formatted sql

--changeset Vitor:3
CREATE TABLE integrante (
	id INT NOT NULL, 
	pessoa_id INT NOT NULL, 
	nome VARCHAR(50) NOT NULL, 
	CONSTRAINT pk_integrante PRIMARY KEY(id), 
	CONSTRAINT fk_pessoa_integrante FOREIGN KEY(pessoa_id) REFERENCES pessoa
);