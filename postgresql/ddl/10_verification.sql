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