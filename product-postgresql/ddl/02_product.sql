
CREATE TABLE IF NOT EXISTS app.product
(
    id uuid NOT NULL,
    title character varying(255) COLLATE pg_catalog."default",
    dtcreate timestamp without time zone,
    dtupdate timestamp without time zone,
    weight integer,
    calories integer,
    carbohydrates double precision,
    fats double precision,
    proteins double precision,
    CONSTRAINT product_pkey PRIMARY KEY (id)
)