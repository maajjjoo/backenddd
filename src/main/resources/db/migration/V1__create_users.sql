CREATE TABLE users (
    id                    BIGSERIAL PRIMARY KEY,
    username              VARCHAR(100) NOT NULL UNIQUE,
    plan                  VARCHAR(20)  NOT NULL DEFAULT 'FREE',
    tokens_used           BIGINT       NOT NULL DEFAULT 0,
    requests_this_minute  INT          NOT NULL DEFAULT 0,
    quota_reset_date      DATE
);

-- Seed three test users (one per plan)
INSERT INTO users (username, plan, quota_reset_date) VALUES
    ('user_free',       'FREE',       DATE_TRUNC('month', NOW()) + INTERVAL '1 month'),
    ('user_pro',        'PRO',        DATE_TRUNC('month', NOW()) + INTERVAL '1 month'),
    ('user_enterprise', 'ENTERPRISE', NULL);
