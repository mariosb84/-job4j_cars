create table owners(
    id serial primary key,
    name VARCHAR,
    user_id int REFERENCES auto_user(id)
);