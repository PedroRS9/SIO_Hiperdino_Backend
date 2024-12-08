CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    warehouse_id INTEGER REFERENCES warehouse(id),
    store_id INTEGER REFERENCES store(id),
    product_id INTEGER REFERENCES product(id),
    quantity INTEGER,
    delivered BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO warehouse (name, location) VALUES
    ('HiperDino Plataforma Logística Telde', 'Lugar Montaña Las Huesas, Polígono Industrial de Salinetas, Montaña las Huesas, Telde, Las Palmas, Canarias, 35215, España'),
    ('HiperDino Plataforma Logística Güímar', 'Polígono Industrial Valle de Güímar Manzana XII - Parcela I, 38550 Arafo, Santa Cruz de Tenerife');