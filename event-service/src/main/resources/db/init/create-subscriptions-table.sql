CREATE TABLE IF NOT EXISTS subscriptions (
    id SERIAL PRIMARY KEY,
    chat_id VARCHAR(15) NOT NULL,
    event_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (chat_id, event_id),
    FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE
)