CREATE TABLE meetings (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    creation_date TIMESTAMP
);

CREATE TABLE participants (
    id BIGSERIAL PRIMARY KEY,
    is_creator BOOLEAN NOT NULL,
    request_status VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    meeting_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    FOREIGN KEY (meeting_id) REFERENCES meetings(id),
    FOREIGN KEY (user_id) REFERENCES users(id),

    UNIQUE (meeting_id, user_id)
)

