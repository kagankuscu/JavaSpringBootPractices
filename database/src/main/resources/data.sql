INSERT INTO authors ("id", "age", "name") VALUES
(1, 30, 'Kagan Kuscu'),
(2, 80, 'Maria Montgramont');

INSERT INTO categories ("id", "name") VALUES
(1, 'Fiction'),
(2, 'Documentary'),
(3, 'Software');

INSERT INTO books ("isbn", "title", "category_id", "author_id") VALUES
('KGN-96-02-11', 'Java Spring Boot', 3, 1),
('KGN-96-02-12', '.Net Development', 3, 2);

