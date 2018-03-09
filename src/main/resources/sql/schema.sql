DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id       INTEGER NOT NULL AUTO_INCREMENT,
  email    VARCHAR(255),
  username VARCHAR(255),
  password VARCHAR(255),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tourists;
CREATE TABLE tourists (
  id       INTEGER NOT NULL AUTO_INCREMENT,
  login VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255),
  mobile VARCHAR(255) NOT NULL UNIQUE ,
  email    VARCHAR(255),
  parent_id INTEGER,
  bonus_amount INTEGER DEFAULT 0,
  used_bonuses_amount INTEGER DEFAULT 0,
  tourist_info_id INTEGER,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tours;
CREATE TABLE tours (
  id       INTEGER NOT NULL AUTO_INCREMENT,
  tourist_id INTEGER NOT NULL,
  cost INTEGER DEFAULT 0,
  tour_info_id INTEGER NOT NULL,
  PRIMARY KEY (id)
);