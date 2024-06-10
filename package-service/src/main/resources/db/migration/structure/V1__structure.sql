-- TABLE - PACKAGES
CREATE TABLE package (
    id BINARY(16) NOT NULL,
    owner_id BINARY(16) NOT NULL,
    tracking_id BINARY(16) NULL,
    company_id BINARY(16) NOT NULL,
    description TEXT,
    weight DOUBLE NOT NULL,
    height DOUBLE NOT NULL,
    width DOUBLE NOT NULL,
    length DOUBLE NOT NULL,
    status VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);