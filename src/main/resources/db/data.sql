DELETE
FROM users;
DELETE
FROM user_roles;
DELETE
FROM questions;
DELETE
FROM results;

INSERT INTO users (login, password)
VALUES ('User', '{noop}password'),
       ('Admin', '{noop}admin'),
       ('testUser', '{noop}test');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100),
       ('ADMIN', 101),
       ('USER', 102);

INSERT INTO questions (question, answer)
VALUES ('Вопрос 1. Вы хотите сдать тест?', 'Да'),
       ('Вопрос 2. Вы хотите сдать тест?', 'Да'),
       ('Вопрос 3. Вы хотите сдать тест?', 'Да'),
       ('Вопрос 4. Вы хотите сдать тест?', 'Да'),
       ('Вопрос 5. Вы хотите сдать тест?', 'Да');

INSERT INTO results (user_id, question_id, user_answer, result)
VALUES (100, 103, 'Да', true),
       (100, 104, 'Да', true),
       (100, 105, 'Да', true),
       (100, 106, 'Да', true),
       (100, 107, 'нет', false),
       (101, 103, 'Да', true),
       (101, 104, 'Да', true),
       (101, 105, 'Да', true),
       (101, 106, 'Да', true),
       (101, 107, 'Да', true),
       (102, 103, 'Да', true),
       (102, 104, 'Да', true),
       (102, 105, 'Нет', false),
       (102, 106, 'Нет', false),
       (102, 107, 'Да', false);
