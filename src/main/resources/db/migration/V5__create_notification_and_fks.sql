CREATE TABLE notifications (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    sended_date TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,
    meeting_id BIGINT NOT NULL,

    FOREIGN KEY (meeting_id) REFERENCES meetings(id),
    FOREIGN KEY (user_id) REFERENCES users(id)

);

ALTER TABLE participants
ALTER COLUMN role DROP NOT NULL;