CREATE TABLE IF NOT EXISTS app.recipe_ingredient
(
    recipe_id uuid NOT NULL,
    product_id uuid NOT NULL,
    weight integer,
    CONSTRAINT product_id_fkey FOREIGN KEY (product_id)
        REFERENCES app.product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT recipe_id_fkey FOREIGN KEY (recipe_id)
        REFERENCES app.recipes (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)