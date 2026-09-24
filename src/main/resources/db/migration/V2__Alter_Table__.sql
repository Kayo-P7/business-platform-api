ALTER TABLE tb_products
    ADD COLUMN title VARCHAR(255) NOT NULL,
    ADD COLUMN description TEXT,
    ADD COLUMN category VARCHAR(255) NOT NULL,
    ADD COLUMN discount_percentage DECIMAL(10, 2),
    ADD COLUMN rating DECIMAL(10, 2),
    ADD COLUMN stock INTEGER,
    ADD COLUMN tags VARCHAR[],
    ADD COLUMN brand VARCHAR(255),
    ADD COLUMN sku VARCHAR(255),
    ADD COLUMN weight INTEGER,

    ADD COLUMN dimensions_width DECIMAL(10, 2) NOT NULL,
    ADD COLUMN dimensions_height DECIMAL(10, 2) NOT NULL,
    ADD COLUMN dimensions_depth DECIMAL(10, 2) NOT NULL,

    ADD COLUMN warranty_information VARCHAR(255),
    ADD COLUMN shipping_information VARCHAR(255),
    ADD COLUMN availability_status VARCHAR(255),

    ADD COLUMN return_policy VARCHAR(255),
    ADD COLUMN minimum_order_quantity INTEGER,

    ADD COLUMN meta_created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    ADD COLUMN meta_update_at TIMESTAMP WITH TIME ZONE NOT NULL,
    ADD COLUMN meta_barcode VARCHAR(255) NOT NULL,
    ADD COLUMN meta_qr_code VARCHAR(255) NOT NULL,

    ADD COLUMN images TEXT[],
    ADD COLUMN thumbnail TEXT,
    ADD COLUMN active BOOLEAN NOT NULL DEFAULT false;


CREATE TABLE tb_products_reviews (
                                     product_id UUID NOT NULL,
                                     rating_review DECIMAL(10, 2),
                                     comment VARCHAR(255),
                                     date TIMESTAMP WITH TIME ZONE,
                                     reviewer_name VARCHAR(255),
                                     reviewer_email VARCHAR(255),

                                     CONSTRAINT fk_product_review
                                         FOREIGN KEY (product_id)
                                             REFERENCES tb_products(id)
);