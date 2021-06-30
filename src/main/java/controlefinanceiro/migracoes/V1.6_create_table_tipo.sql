--liquibase formatted sql

--changeset Vitor:6
CREATE TABLE tipo (
	id INT NOT NULL, 
	descricao VARCHAR(50) NOT NULL, 
	CONSTRAINT pk_receita PRIMARY KEY(id), 
);