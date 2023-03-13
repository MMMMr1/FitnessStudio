CREATE TABLE IF NOT EXISTS app.users
(
    id uuid NOT NULL,
    mail character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    fio character varying(255) COLLATE pg_catalog."default",
    dtcreate timestamp without time zone,
    dtupdate timestamp without time zone,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_mail_key UNIQUE (mail)
)