INSERT INTO BOOK (ISBN, TITLE, DATE_ADDED) VALUES
('5-01-013850-9', 'title1', '2021-03-1'),
('5-02-013850-9', 'title2', '2021-03-2'),
('5-03-013850-9', 'title3', '2021-03-3'),
('5-04-013850-9', 'title4', '2021-03-4'),
('5-05-013850-9', 'title5', '2021-03-5'),
('5-06-013850-9', 'title6', '2021-03-6'),
('5-07-013850-9', 'title7', '2021-03-7'),
('5-08-013850-9', 'title8', '2021-03-8'),
('5-09-013850-9', 'title9', '2021-03-9'),
('5-10-013850-9', 'title10', '2021-03-10'),
('5-11-013850-9', 'title11', '2021-03-11'),
('5-12-013850-9', 'title12', '2021-03-12');

INSERT INTO BOOK_GENRE (BOOK_ID, GENRES) VALUES
('5-01-013850-9', 'FICTION'),
('5-01-013850-9', 'NARRATIVE'),
('5-02-013850-9', 'POETRY'),
('5-03-013850-9', 'NOVEL'),
('5-04-013850-9', 'PROSE'),
('5-04-013850-9', 'DRAMA'),
('5-05-013850-9', 'NON_FICTION'),
('5-06-013850-9', 'SHORT_STORY'),
('5-07-013850-9', 'DRAMA'),
('5-08-013850-9', 'NOVEL'),
('5-09-013850-9', 'NOVEL'),
('5-10-013850-9', 'DRAMA'),
('5-11-013850-9', 'NON_FICTION'),
('5-11-013850-9', 'POETRY'),
('5-12-013850-9', 'SHORT_STORY');

INSERT INTO BOOK_TO_AUTHOR (BOOK_ID, AUTHORS) VALUES
('5-01-013850-9', 'Author1'),
('5-02-013850-9', 'Author21'),
('5-02-013850-9', 'Author22'),
('5-03-013850-9', 'Author3'),
('5-04-013850-9', 'Author4'),
('5-05-013850-9', 'Author51'),
('5-05-013850-9', 'Author52'),
('5-06-013850-9', 'Author6'),
('5-07-013850-9', 'Author7'),
('5-08-013850-9', 'Author8'),
('5-09-013850-9', 'Author9'),
('5-10-013850-9', 'Author3'),
('5-10-013850-9', 'Author4'),
('5-11-013850-9', 'Author8'),
('5-12-013850-9', 'Author9');

INSERT INTO customer_role (role_id, name) VALUES
(1, 'ADMIN'),
(2, 'CUSTOMER');

INSERT INTO customer_permission (permission_id, permission) VALUES
(1, 'ADD_BOOKS'),
(2, 'MNG_FAVORITES');

INSERT INTO role_to_permission (role_id, permission_id) VALUES
(1, 1),
(1, 2),
(2, 2);

INSERT INTO CUSTOMER (username, email, enpassword, role_id) VALUES
('user1', 'user1@ukr.net', 'NDQ0NDQ0NDQ=', 1), -- 44444444
('user2', 'user2@ukr.net', 'NDQ0NDQ0NDQ=', 2);