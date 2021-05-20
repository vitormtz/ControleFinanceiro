--liquibase formatted sql

--changeset Vitor:2
CREATE TABLE pessoa (
	id INT NOT NULL, 
	nome VARCHAR(50) NOT NULL, 
	email VARCHAR(75) NOT NULL, 
	senha VARCHAR(100) NOT NULL, 
	CONSTRAINT pk_pessoa PRIMARY KEY(id), 
	CONSTRAINT uq_pessoa_email UNIQUE(email)
);