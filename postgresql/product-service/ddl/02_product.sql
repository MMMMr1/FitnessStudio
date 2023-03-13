
CREATE TABLE IF NOT EXISTS app.product
(
    id uuid NOT NULL,
    calories integer,
    carbohydrates double precision,
    dtcreate timestamp without time zone,
    dtupdate timestamp without time zone,
    fats double precision,
    proteins double precision,
    title character varying(255) COLLATE pg_catalog."default",
    weight integer,
    CONSTRAINT product_pkey PRIMARY KEY (id),
    CONSTRAINT product_title_key UNIQUE (title)
)