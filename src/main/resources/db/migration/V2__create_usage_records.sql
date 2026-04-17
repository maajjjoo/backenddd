CREATE TABLE usage_records (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL REFERENCES users(id),
    date        DATE   NOT NULL,
    tokens_used BIGINT NOT NULL DEFAULT 0,
    UNIQUE (user_id, date)
);
