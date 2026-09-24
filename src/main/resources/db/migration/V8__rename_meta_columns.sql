ALTER TABLE tb_products
    RENAME COLUMN meta_created_at TO created_at;

ALTER TABLE tb_products
    RENAME COLUMN meta_update_at TO update_at;

ALTER TABLE tb_products
    RENAME COLUMN meta_barcode TO barcode;

ALTER TABLE tb_products
    RENAME COLUMN meta_qr_code TO qr_code;