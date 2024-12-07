INSERT INTO product (name, price, min_stock, max_stock) VALUES
    ('Leche', 1.20, 10, 100),
    ('Pan', 0.90, 20, 200),
    ('Huevos', 2.50, 5, 50),
    ('Mantequilla', 1.50, 8, 80),
    ('Queso', 3.00, 5, 50),
    ('Pechuga de Pollo', 5.00, 3, 30),
    ('Manzanas', 2.00, 15, 150),
    ('Plátanos', 1.80, 10, 100),
    ('Zumo de Naranja', 2.50, 7, 70),
    ('Cereal', 3.50, 6, 60);

-- Insertar datos en la tabla Store
INSERT INTO store (name, location) VALUES
                                       ('Hiperdino Triana', 'C. Munguía, 7, Las Palmas de Gran Canaria'),
                                       ('Superdino General Bravo', 'C. Gral. Bravo, 17, Las Palmas de Gran Canaria'),
                                       ('Hiperdino Miller', 'C. Luis Correa Medina, Las Palmas de Gran Canaria'),
                                       ('Hiperdino Luis Doreste', 'C. Luis Doreste Silva, Las Palmas de Gran Canaria'),
                                       ('Hiperdino El Batán', 'C. Severo Ochoa, 5, Las Palmas de Gran Canaria');

-- Insertar datos en la tabla Inventory
INSERT INTO inventory (product_id, store_id, quantity) VALUES
                                                           (1, 1, 50),
                                                           (2, 1, 100),
                                                           (3, 2, 30),
                                                           (4, 3, 20),
                                                           (5, 4, 25),
                                                           (6, 5, 15),
                                                           (7, 1, 60),
                                                           (8, 2, 40),
                                                           (9, 3, 35),
                                                           (10, 4, 45),
                                                           (1, 5, 55),
                                                           (2, 5, 90),
                                                           (3, 4, 25),
                                                           (4, 3, 18),
                                                           (5, 2, 22),
                                                           (6, 1, 12),
                                                           (7, 5, 70),
                                                           (8, 4, 50),
                                                           (9, 3, 28),
                                                           (10, 2, 38);