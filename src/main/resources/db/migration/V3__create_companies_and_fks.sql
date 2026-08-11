CREATE TABLE companies (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP
);

ALTER TABLE users
    ADD COLUMN company_id BIGINT,
    ADD CONSTRAINT fk_user_company FOREIGN KEY (company_id) REFERENCES companies(id)
;