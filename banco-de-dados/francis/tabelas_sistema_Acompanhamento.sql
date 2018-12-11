CREATE DATABASE fat_protocolo
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;




				--TABELA ATENDENTE--
CREATE SEQUENCE id_solicitante
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 1000000000009
	START 1
	CACHE 1;
CREATE TABLE solicitante 
(
  id           	 				INTEGER NOT NULL DEFAULT NEXTVAL('id_solicitante'::regclass),
  nome 	         	 			VARCHAR(50) NOT NULL,
  telefone         	 			VARCHAR(50) NOT NULL,
  cpf							CHARACTER VARYING (20) NOT NULL,	
  data_cadastro				    DATE NOT NULL DEFAULT ('now'::text)::DATE,
  CONSTRAINT pk_atendente_id PRIMARY KEY (id)
);

		--TABELA USUARIO--
CREATE SEQUENCE id_usuario
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 1000000000009
	START 1
	CACHE 1;
CREATE TABLE usuario
(
	id				INTEGER NOT NULL  ,
	nome			CHARACTER VARYING (60) NOT NULL,
	login			CHARACTER VARYING (10) NOT NULL,
	senha			CHARACTER VARYING (10) NOT NULL,
	data_cadastro	DATE NOT NULL DEFAULT ('now'::text)::DATE,
	CONSTRAINT pk_usuario_id PRIMARY KEY (id)
);

			--TABELA PROTOCOLO--
CREATE SEQUENCE id_protocolo
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 1000000000009
	START 1
	CACHE 1;
CREATE TABLE protocolo
(
	id							INTEGER NOT NULL DEFAULT NEXTVAL('id_protocolo'::regclass),
	n_protocolo					INTEGER NOT NULL,
	destinatario				INTEGER NOT NULL,
	situacao					CHARACTER VARYING (1) NOT NULL DEFAULT 'A' ::bpchar,
	data_previsa_retorno		DATE,
	data_lanctos				DATE NOT NULL DEFAULT ('now'::text)::DATE,
	numero_livro				INTEGER  NOT NULL,
	pagina						INTEGER  NOT NULL,
	id_solicitante				INTEGER NOT NULL,
	observacao					CHARACTER VARYING (100),
	id_usuario					INTEGER NOT NULL,
	CONSTRAINT pk_protocolo_id PRIMARY KEY (id),
	CONSTRAINT fk_protocolo_usuario_id FOREIGN KEY (id_usuario) 
    REFERENCES usuario (id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT fk_protocolo_solicitante_id FOREIGN KEY (id_solicitante) 
    REFERENCES solicitante (id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT ck_situacao CHECK (situacao = ANY (ARRAY['A'::bpchar, 'F'::bpchar]))
);

			--TABELA PROTOCOLO--
CREATE TABLE protocolo_movimentacao
(
	data_movimentacao			DATE,
	motivo						CHARACTER VARYING (30) NOT NULL,
	n_protocolo					INTEGER NOT NULL,
	id_usuario_movimentacao		INTEGER NOT NULL,
	
	CONSTRAINT fk_movimentacao_id_usuario FOREIGN KEY (id_usuario_movimentacao) 
    REFERENCES usuario (id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT
);





