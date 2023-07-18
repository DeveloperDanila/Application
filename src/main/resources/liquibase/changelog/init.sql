CREATE TABLE photo (
    id SERIAL PRIMARY KEY,
    content bytea
) ;


CREATE TABLE author (
    id SERIAL PRIMARY KEY,
    name varchar(150)
) ;

CREATE TABLE trash (
    id SERIAL PRIMARY KEY,
    title varchar(150),
    price numeric,
    description text,
    photo_id SERIAL,
    author_id SERIAL,

    FOREIGN KEY (photo_id) REFERENCES photo(id),
    FOREIGN KEY (author_id) REFERENCES author(id)


);
