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