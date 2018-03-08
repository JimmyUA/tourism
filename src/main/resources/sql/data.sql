INSERT INTO users (email, username, password)
VALUES ('sergii.pryhodko@gmail.com', 'badmin', '$2y$10$AgTGIdGAq0gY6uDRDK2awu52hzFmHvbYVzr298fYg3/aGeWDWW4je');

INSERT INTO tourists (login, password, mobile, email, bonus_amount, used_bonuses_amount, tourist_info_id)
VALUES ('jimmy', 'sdfdsfdsg', '+380993551572', 'sergii.pryhodko@gmail.com',  10000, 0, 1);

INSERT INTO tourists (login, password, mobile, email, parent_id, bonus_amount, used_bonuses_amount, tourist_info_id)
VALUES ('jimmy1', 'sdfdsfdsg', '+380993551573', 'sergii.pryhodko@gmail.ua', 1, 20000, 243, 2);

INSERT INTO tours (tourist_id, cost, tour_info_id)
VALUES (1, 20000, 1);