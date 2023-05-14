--liquibase formatted sql

--changeset mmichalenok:1
CREATE SCHEMA  IF NOT EXISTS  app AUTHORIZATION test;

--changeset mmichalenok:2
CREATE TABLE IF NOT EXISTS app.role
(
    role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (role)
)
--rollback DROP TABLE app.role;

--changeset mmichalenok:3

CREATE TABLE IF NOT EXISTS app.status
(
    status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT status_pkey PRIMARY KEY (status)
)
--rollback DROP TABLE app.status;


--changeset mmichalenok:4
CREATE TABLE IF NOT EXISTS app.users
(
    id uuid NOT NULL,
    mail character varying(255) COLLATE pg_catalog."default" NOT NULL,
    fio character varying(255) COLLATE pg_catalog."default",
    dtcreate timestamp without time zone,
    dtupdate timestamp without time zone,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_mail_key UNIQUE (mail)
)
--rolllback DROP TABLE app.users;

--changeset mmichalenok:5
CREATE TABLE IF NOT EXISTS app.user_status
(
    user_id uuid NOT NULL,
    status_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_status_pkey PRIMARY KEY (user_id),
    CONSTRAINT status_id_fkey FOREIGN KEY (status_id)
        REFERENCES app.status (status) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT user_id_fkey FOREIGN KEY (user_id)
        REFERENCES app.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
--rollback DROP TABLE app.user_status;

--changeset mmichalenok:6
CREATE TABLE IF NOT EXISTS app.user_role
(
    user_id uuid NOT NULL,
    role_id character varying COLLATE pg_catalog."default",
    CONSTRAINT user_role_pkey PRIMARY KEY (user_id),
    CONSTRAINT role_id_fkey FOREIGN KEY (role_id)
        REFERENCES app.role (role) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT user_id_fkey FOREIGN KEY (user_id)
        REFERENCES app.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
--rollback DROP TABLE app.user_role;

--changeset mmichalenok:7
CREATE TABLE IF NOT EXISTS app.verification
(
    id uuid NOT NULL,
    code character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT verification_pkey PRIMARY KEY (id),
    CONSTRAINT id_fkey FOREIGN KEY (id)
        REFERENCES app.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
--rollback DROP TABLE app.verification;