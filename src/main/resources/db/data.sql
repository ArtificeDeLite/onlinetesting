DELETE
FROM results;
DELETE
FROM user_roles;
DELETE
FROM users;

DELETE
FROM questions;


INSERT INTO users (login, password)
VALUES ('User', '{noop}password'),
       ('Admin', '{noop}admin'),
       ('testUser', '{noop}test');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100),
       ('ADMIN', 101),
       ('USER', 102);

INSERT INTO questions (question, answer)
VALUES ('Принцип в программировании\n1.SOLID\n2.COVID\n3.DAMIT\n4.BANDIT', '1'),
       ('Какой элемент переодической системы химических элементов обозначается как Ag?', 'Серебро'),
       ('Единица измерения силы тока\n1.Ампер\n2.Вольт\n3.Ватт\n4.Ом', '1'),
       ('Самое глубокое озеро на планете?', 'Байкал'),
       ('Какой город изображен на современной российской купюре 1000р?', 'Ярославль');

INSERT INTO results (user_id, question_id, user_answer, result)
VALUES (100, 103, '1', true),
       (100, 104, 'Серебро', true),
       (100, 105, '1', true),
       (100, 106, 'Байкал', true),
       (100, 107, 'Москва', false),
       (101, 103, '1', true),
       (101, 104, 'Серебро', true),
       (101, 105, '1', true),
       (101, 106, 'Байкал', true),
       (101, 107, 'Ярославль', true),
       (102, 103, '1', true),
       (102, 104, 'Серебро', true),
       (102, 105, '2', false),
       (102, 106, 'Ладожское', false),
       (102, 107, 'Хабаровск', false);
