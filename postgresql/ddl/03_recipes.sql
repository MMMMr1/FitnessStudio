CREATE TABLE IF NOT EXISTS app.recipes
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone,
    dt_update timestamp without time zone,
    title character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT recipes_pkey PRIMARY KEY (uuid)
)
