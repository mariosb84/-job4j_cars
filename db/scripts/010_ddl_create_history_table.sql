create table history(
    id serial primary key,
    startAt TImestamp,
    endAt TImestamp,
    owner_hist_id int not null references owners(id),
    car_hist_id int not null references car(id)
);