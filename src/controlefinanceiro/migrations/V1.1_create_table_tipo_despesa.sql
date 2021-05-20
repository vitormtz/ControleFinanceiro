--liquibase formatted sql

--changeset Julio:1
CREATE TABLE tipo_despesa (
	id INT NOT NULL, 
	descricao VARCHAR(25) NOT NULL, 
	CONSTRAINT pk_tipo_despesa PRIMARY KEY(id), 
	CONSTRAINT uq_tipo_despesa_descricao UNIQUE(descricao)
);