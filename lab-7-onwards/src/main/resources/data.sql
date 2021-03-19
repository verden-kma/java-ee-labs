INSERT INTO BOOK (ISBN, TITLE, DATE_ADDED) VALUES
('1', 'title1', '2021-03-1'),
('2', 'title2', '2021-03-2'),
('3', 'title3', '2021-03-3'),
('4', 'title4', '2021-03-4'),
('5', 'title5', '2021-03-5'),
('6', 'title6', '2021-03-6'),
('7', 'title7', '2021-03-7'),
('8', 'title8', '2021-03-8'),
('9', 'title9', '2021-03-9'),
('10', 'title10', '2021-03-10'),
('11', 'title11', '2021-03-11'),
('12', 'title12', '2021-03-12');

INSERT INTO BOOK_GENRE (BOOK_ID, GENRES) VALUES
('1', 'FICTION'),
('1', 'NARRATIVE'),
('2', 'POETRY'),
('3', 'NOVEL'),
('4', 'PROSE'),
('4', 'DRAMA'),
('5', 'NON_FICTION'),
('6', 'SHORT_STORY'),
('7', 'DRAMA'),
('8', 'NOVEL'),
('9', 'NOVEL'),
('10', 'DRAMA'),
('11', 'NON_FICTION'),
('11', 'POETRY'),
('12', 'SHORT_STORY');

INSERT INTO BOOK_TO_AUTHOR (BOOK_ID, AUTHORS) VALUES
('1', 'Author1'),
('2', 'Author21'),
('2', 'Author22'),
('3', 'Author3'),
('4', 'Author4'),
('5', 'Author51'),
('5', 'Author52'),
('6', 'Author6'),
('7', 'Author7'),
('8', 'Author8'),
('9', 'Author9'),
('10', 'Author3'),
('10', 'Author4'),
('11', 'Author8'),
('12', 'Author9');

INSERT INTO CUSTOMER (username, email, enpassword) VALUES
('user1', 'user1@ukr.net', 'MTExMQ=='),
('user2', 'user2@ukr.net', 'MTExMQ==');
