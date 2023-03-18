
CREATE TABLE IF NOT EXISTS app.audit
(
    id uuid NOT NULL,
    dtcreate timestamp without time zone,
    type_of_audit character varying(255) COLLATE pg_catalog."default",
    text character varying(255) COLLATE pg_catalog."default",
    id_modified character varying(255) COLLATE pg_catalog."default",
    uuid_user character varying(255) COLLATE pg_catalog."default",
    mail_user character varying(255) COLLATE pg_catalog."default",
    fio_user character varying(255) COLLATE pg_catalog."default",
    role_user character varying(255) COLLATE pg_catalog."default",

    CONSTRAINT audit_pkey PRIMARY KEY (id)
 )