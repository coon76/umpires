INSERT INTO umpire
(id, first_name, last_name, senior)
VALUES
(1, 'Joseph', 'Kuhn', true),
(2, 'Molly', 'Kuhn', false);

INSERT INTO umpire_season
(id, umpire_id, season)
VALUES
(1, 1, 2021),
(2, 1, 2022),
(3, 2, 2022);

INSERT INTO event_dates
(id, game_time, event, rain_out)
VALUES
(1, '2023-04-15 13:00:00', 1, false);

INSERT INTO location
(id, short_desc, long_desc)
VALUES
(1, '2', 'Walden Pond #2')