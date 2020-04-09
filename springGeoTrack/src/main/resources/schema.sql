create table if not exists tbl_user(
  id SERIAL PRIMARY KEY,
  name text NOT NULL,
  email text NOT NULL
);

create table if not exists track_location(
  id serial NOT NULL PRIMARY KEY,
  country_code text DEFAULT NULL,
  country text DEFAULT NULL,
  province text DEFAULT NULL,
  city text DEFAULT NULL,
  street text DEFAULT NULL,
  house text DEFAULT NULL,
  postal_code int4 DEFAULT NULL
);


create table if not exists track(
  id serial NOT NULL PRIMARY KEY,
  lat numeric(9,6) NOT NULL,
  lng numeric(9,6) NOT NULL,
  created_at timestamp not null default CURRENT_TIMESTAMP,
  user_id int4 NOT NULL,
  track_location_id int4 DEFAULT NULL
--  KEY track_user_id_fk (user_id),
--  KEY track_track_location_id_fk (track_location_id),
--  CONSTRAINT track_track_location_id_fk FOREIGN KEY (track_location_id) REFERENCES track_location (id),
--  CONSTRAINT track_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id)
);