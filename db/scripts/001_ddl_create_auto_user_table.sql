CREATE TABLE if not exists auto_user (
  id SERIAL PRIMARY KEY,
  login VARCHAR NOT NULL UNIQUE,
  password VARCHAR NOT NULL UNIQUE
);

