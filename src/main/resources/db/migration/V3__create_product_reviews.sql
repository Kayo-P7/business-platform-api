CREATE TABLE product_reviews (
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