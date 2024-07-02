INSERT INTO filter (name) VALUES ('Filter 1');
INSERT INTO filter (name) VALUES ('Filter 2');

INSERT INTO criteria (type, comparison, value_text, filter_id) VALUES ('text', '=', 'example', 1);
INSERT INTO criteria (type, comparison, value_number, filter_id) VALUES ('number', '=', 1234, 1);
INSERT INTO criteria (type, comparison, value_number, filter_id) VALUES ('number', '>', 100, 2);
INSERT INTO criteria (type, comparison, value_date, filter_id) VALUES ('date', '>', '2022-01-17', 2);
INSERT INTO criteria (type, comparison, value_date, filter_id) VALUES ('date', '<', '2024-07-01', 2);
