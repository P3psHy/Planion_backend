CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    telephone VARCHAR(10),
    location VARCHAR(255),

    mail VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255)
);