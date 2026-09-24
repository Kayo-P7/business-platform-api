ALTER TABLE tb_products
    RENAME COLUMN dimensions_width TO width;

ALTER TABLE tb_products
    RENAME COLUMN dimensions_height TO height;

ALTER TABLE tb_products
    RENAME COLUMN dimensions_depth TO depth;