CREATE TABLE company (
    id BINARY(16) NOT NULL,
    ruc VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_by BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (ruc)
);

CREATE TABLE employee (
    id BINARY(16) NOT NULL,
    user_id BINARY(16) NOT NULL,
    company_id BINARY(16),
    PRIMARY KEY (id),
    CONSTRAINT fk_employee_company FOREIGN KEY (company_id) REFERENCES company(id)
);

CREATE TABLE client (
    id BINARY(16) NOT NULL,
    names VARCHAR(120) NOT NULL,
    surnames VARCHAR(120) NOT NULL,
    dni VARCHAR(8) NOT NULL,
    email VARCHAR(240) NULL,
    phone_number VARCHAR(9) NULL,
    company_id BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (dni),
    CONSTRAINT fk_client_company FOREIGN KEY (company_id) REFERENCES company(id)
);