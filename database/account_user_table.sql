CREATE TABLE account
(
    account_id SERIAL PRIMARY KEY,
    email  TEXT NOT NULL,
    username TEXT NOT NULL,
    password   TEXT NOT NULL
);

ALTER SEQUENCE account_account_id_seq RESTART 1000000;