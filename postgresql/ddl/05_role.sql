CREATE TABLE IF NOT EXISTS app.role
(
    role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (role)
)