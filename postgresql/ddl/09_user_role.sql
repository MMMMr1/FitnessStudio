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
